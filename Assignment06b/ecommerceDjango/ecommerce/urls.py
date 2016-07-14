from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^reports/daily-sale', views.audit_logs_by_startdate_enddate, name='audit_logs_by_startdate_enddate'),
]