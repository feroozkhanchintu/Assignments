from django.http import HttpResponse
import datetime
from models import OrderDetails
from django.db.models import F, FloatField
from django.db.models import Count, Sum
from django.http import JsonResponse

def audit_logs_by_startdate_enddate(request):
    startDate = request.GET.get('startDate', '01/31/2000')
    startDate = datetime.datetime.strptime(startDate, '%m/%d/%Y').strftime('%Y-%m-%d')
    endDate = request.GET.get('endDate', '01/31/2050')
    endDate = datetime.datetime.strptime(endDate, '%m/%d/%Y').strftime('%Y-%m-%d')

    order_details = OrderDetails.objects.filter(order__order_date__gte=startDate, order__order_date__lte=endDate).values('order__order_date').order_by('-order__order_date')
    order_details = order_details.annotate(date= F('order__order_date'), orders=Count('order__order_id'), qty=Sum('quantity_ordered'))
    order_details = order_details.annotate(sale_price=Sum(F('selling_price') * F('quantity_ordered'), output_field=FloatField()), buy_price=Sum(F('cost_price') * F('quantity_ordered'), output_field=FloatField()))
    order_details = order_details.annotate(profit=Sum(F('selling_price') * F('quantity_ordered'), output_field=FloatField()) - Sum(F('cost_price') * F('quantity_ordered'), output_field=FloatField()))
    order_details = list(order_details)

    for od in order_details:
        del od['order__order_date']
        od['date'] = datetime.date.strftime(od['date'], "%m/%d/%Y")
        od['sale_price'] = round(od['sale_price'], 2)
        od['buy_price'] = round(od['buy_price'], 2)
        od['profit'] = round(od['profit'], 2)

    return JsonResponse({'data': order_details})