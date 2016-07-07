1. 
	select ORDER_DATE, SUM(QUANTITY_ORDERED*SELLING_PRICE) AS SALES 
	from ORDERS INNER JOIN ORDER_DETAILS 
	where ORDERS.ORDER_ID=ORDER_DETAILS.ORDER_ID AND ORDERS.`STATUS` != 'Cancelled'
	GROUP BY ORDER_DATE

2.
select ORDER_DATE, SUM(QUANTITY_ORDERED*SELLING_PRICE - QUANTITY_ORDERED*COST_PRICE) AS PROFIT 
from ORDERS INNER JOIN ORDER_DETAILS 
where ORDERS.ORDER_ID=ORDER_DETAILS.ORDER_ID  AND ORDERS.`STATUS` != 'Cancelled'
GROUP BY ORDER_DATE

3.
sselect COMPANY_NAME, ORDERS.USER_ID, AVG(QUANTITY_ORDERED) AS AVERAGE
from ORDERS 
INNER JOIN ORDER_DETAILS 
on ORDERS.ORDER_ID=ORDER_DETAILS.ORDER_ID 
INNER JOIN USER_DETAILS
on ORDERS.USER_ID = USER_DETAILS.USER_ID
AND ORDERS.`STATUS` != 'Cancelled'
GROUP BY USER_ID

4.
UPDATE PRODUCT SET IS_AVAILABLE='0' where `PRODUCT_ID`=product_id

5.
INSERT IGNORE INTO `USER_DETAILS` SET 'COMPANY_NAME'=customerName, 'CONTACT_FIRST_NAME'=contactFirstName, 'CONTACT_LAST_NAME'=contactLastName,
				'PHONE'=phone, 'ADDRESS_LINE'=address, 'CITY'=city, 'STATE'=state, 'POSTAL_CODE'=postalCode, 'COUNTRY'=country;

INSERT INTO ORDERS('USER_ID', 'ORDER_ID', 'ORDER_DATE', 'STATUS') 
VALUES('user_id', 'order_id', 'order_date', 'status');

INSERT INTO ORDER_DETAILS('ORDER_ID', 'PRODUCT_ID', 'QUANTITY_ORDERED', 'COST_PRICE', 'COST_PRICE', 'SELLING_PRICE') 
VALUES ('order_id', 'product_id', 'quantityOrdered', 'costPrice', 'sellingPrice')
