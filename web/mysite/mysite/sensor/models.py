from mongoengine import *
connect('sensor')

class Data(Document):
    platform = StringField(max_length=500, required=True)
    device_id = StringField(max_length=500, required=True) 
    time =  StringField(max_length=500, required=True) 
    type_s  = StringField(max_length=500, required=True)
    data_s  =  StringField(max_length=500, required=True)
# Create your models here.
