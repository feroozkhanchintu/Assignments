import datetime

import boto3
import json
from sqlalchemy import *
import config

connection_string = "mysql+pymysql://" + config.USERNAME + ":" + config.PASSWORD + "@" + config.HOST + "/" + config.DATABASE
engine = create_engine(connection_string)
metadata = MetaData()
connection = engine.connect()
product = Table('LOG_DATA', metadata, autoload=True, autoload_with=engine)

class SqsToRds:
    def readFromSqs(self):
        # Get the service resource
        sqs = boto3.resource('sqs')

        # Get the queue. This returns an SQS.Queue instance
        queue = sqs.get_queue_by_name(QueueName='cnu2016_vjain_assignment05a')

        # You can now access identifiers and attributes
        print(queue.url)

        # Process messages by printing out body and optional author name
        for message in queue.receive_messages():
            # Get the custom author message attribute if it was set
            print(message.body)
            sqs_data = json.loads(message.body)
            timestamp  = sqs_data['timestamp']
            t = datetime.datetime.fromtimestamp(float(timestamp) / 1000.0)
            fmt = "%Y-%m-%d %H:%M:%S"
            timestamp = t.strftime(fmt)
            url = sqs_data['url']
            parameters = sqs_data['parameters']
            print (type(parameters))
            responseCode = sqs_data['responseCode']
            ipAddress = sqs_data['ipAddress']
            completionTime=sqs_data['completionTime']
            requestType = sqs_data['requestType']
            print (parameters.encode('utf-8'))
            ins = product.insert().values(TIMESTAMP=timestamp, URL=url, PARAMETERS=parameters, RESPONSECODE=responseCode,
                                          IPADDRESS=ipAddress, EXECUTIONTIME=completionTime, REQUESTTYPE=requestType)
            res = connection.execute(ins)
            message.delete()

str = SqsToRds()
# t = threading.Timer(2, str.readFromSqs)
# t.start()
while True:
    import time
    str.readFromSqs()
    time.sleep(5)

# str.readFromSqs()




