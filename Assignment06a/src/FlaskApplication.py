__author__ = 'VipulJain'

from flask import Flask
from flask import jsonify
from flask_sqlalchemy import SQLAlchemy
import config
import dateutil.parser as parser
from flask import request
from datetime import date
import dateutil

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = "mysql+pymysql://" + config.USERNAME + \
                                        ":" + config.PASSWORD + "@" + config.HOST + "/" + config.DATABASE
db = SQLAlchemy(app)

class LOG_DATA(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    TIMESTAMP = db.Column(db.DATETIME)
    URL = db.Column(db.VARCHAR)
    PARAMETERS = db.Column(db.VARCHAR)
    RESPONSECODE = db.Column(db.INTEGER)
    IPADDRESS = db.Column(db.VARCHAR)
    EXECUTIONTIME = db.Column(db.INTEGER)
    REQUESTTYPE = db.Column(db.VARCHAR)

    def __init__(self, id, TIMESTAMP, URL, PARAMETERS, REPONSECODE,
                 IPADDRESS, EXECUTIONTIME, REQUESTTYPE):
        self.id = id
        self.TIMESTAMP = TIMESTAMP
        self.URL = URL
        self.PARAMETERS = PARAMETERS
        self.RESPONSECODE = REPONSECODE
        self.IPADDRESS = IPADDRESS
        self.EXECUTIONTIME = EXECUTIONTIME
        self.REQUESTTYPE = REQUESTTYPE

    def to_JSON(self):
        return {"id": self.id,
                "url": self.URL,
                "parameters": self.PARAMETERS, "response_code": self.RESPONSECODE,
                "request_ip_address": self.IPADDRESS, "timestamp": self.convertDateToIso(self.TIMESTAMP),
                "request_duration_ms": self.EXECUTIONTIME, "request_type": self.REQUESTTYPE
                }

    def convertDateToIso(self, dates):
        dates = str(dates)
        ret = parser.parse(dates)
        return ret.isoformat()

    def __repr__(self):
        return '<Post %r>' % self.URL


@app.route("/api/auditLogs", methods=['GET'])
def get_audit_logs():
    limit = min(10, int(request.args.get('limit', 10)))
    offset = request.args.get('offset', 0)
    print(limit)
    startTime = request.args.get('startTime', '2015-01-01 00:00:00')
    endTime = request.args.get('endTime', '3000-12-10 00:00:00')
    startTime = dateutil.parser.parse(startTime)
    endTime = dateutil.parser.parse(endTime)
    return jsonify(data=[logs.to_JSON() for logs in LOG_DATA.query
            .order_by(LOG_DATA.TIMESTAMP.desc())
            .filter(LOG_DATA.TIMESTAMP.between(startTime, endTime))
            .limit(limit).offset(offset).all()])

if __name__ == '__main__':
    # print(LOG_DATA.query.filter(LOG_DATA.EXECUTIONTIME >= date.today()).all())
    app.run(debug=True)



