from sqlalchemy import *
import csv
import sqlalchemy
import sqlalchemy.ext.declarative
import sqlalchemy.orm.interfaces
import sqlalchemy.exc
import config

conncetion_string = "mysql+pymysql://" + config.USERNAME + ":" + config.PASSWORD + "@" + config.HOST + "/" + config.DATABASE
engine = create_engine(conncetion_string)
metadata = MetaData()
connection = engine.connect()


users = Table('USER_DETAILS', metadata, autoload=True, autoload_with=engine)
orders = Table('ORDER', metadata, autoload=True, autoload_with=engine)
order_details = Table('ORDER_DETAILS', metadata, autoload=True, autoload_with=engine)
product = Table('PRODUCT', metadata, autoload=True, autoload_with=engine)

with open('assignment03-load.csv', 'rb') as csvfile:
	reader = csv.DictReader(csvfile)
	for row in reader:
		customerName = row['customerName']
		contactFirstName = row['contactFirstName']
		contactLastName = row['contactLastName']
		phone = row['phone']
		address = row['addressLine1']
		city = row['city']
		state = row['state']
		postalCode = row['postalCode']
		country = row['country']
		orderDate = row['orderDate']
		status = row['status']
		productCode = row['productCode']
		productName = row['productName']
		quantityOrdered = row['quantityOrdered']
		sellingPrice = row['priceEach']
		productDescription = row['productDescription']
		quantityInStock = row['quantityInStock']
		costPrice = row['buyPrice']

		if not customerName or not productCode or not productName:
			continue

		# insertion into user database
		user_id = None
		
		sel = select([users.c.USER_ID]).where(users.c.COMPANY_NAME==customerName)
		for row in connection.execute(sel):
			user_id = row[0]
		if (not user_id):
			ins = users.insert().values(COMPANY_NAME=customerName, CONTACT_FIRST_NAME=contactFirstName, CONTACT_LAST_NAME=contactLastName,
				PHONE=phone, ADDRESS_LINE=address, CITY=city, STATE=state, POSTAL_CODE=postalCode, COUNTRY=country)
			res = connection.execute(ins)
			user_id = res.inserted_primary_key[0]
		print(user_id)

		# insertion into orders database

		ins = orders.insert().values(USER_ID=user_id, ORDER_DATE=orderDate, STATUS=status)
		res = connection.execute(ins)
		order_id = res.inserted_primary_key[0]
		print(order_id)

		# insertion into product database
		product_id = None

		sel = select([product.c.PRODUCT_ID]).where(product.c.PRODUCT_CODE==productCode)
		for row in connection.execute(sel):
			product_id = row[0]
		if (not product_id):
			ins = product.insert().values(PRODUCT_DESCRIPTION=productDescription, COST_PRICE='1000', SELLING_PRICE='1000',
				QUANTITY_IN_STOCK=quantityInStock, PRODUCT_CODE=productCode, PRODUCT_NAME=productName)
			res = connection.execute(ins)
			product_id = res.inserted_primary_key[0]
		print(product_id)

		# insertion into order_details database
		ins = order_details.insert().values(ORDER_ID=order_id, PRODUCT_ID=product_id, QUANTITY_ORDERED=quantityOrdered, COST_PRICE=costPrice, 
			SELLING_PRICE=sellingPrice)
		res = connection.execute(ins)