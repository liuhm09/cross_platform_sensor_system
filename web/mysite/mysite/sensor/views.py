from django.shortcuts import render_to_response
from django.template import RequestContext
from models import Data
import datetime
a = range(0, 20)
# b = range(0, 12)
def index(request):
    if request.method == 'GET':
       # save new GET
       platform = request.GET['platform']
       print platform
       device_id = request.GET['id']
       print device_id
       time = request.GET['time']
       print time 
       type_s  = request.GET['type']
       print type_s
       data_s  = request.GET['data']
       print data_s
       
       # d = data()
       # d.platform = platform
       # d.time = time
       # d.type_s = type_s
       # d.data_s = data_s

       d = Data()
       d.platform = platform
       d.time = time
       d.type_s = type_s
       d.data_s = data_s
       d.device_id = device_id


       d.save()
    
    # GET all GETs from DB
    Datas = Data.objects 
    return render_to_response('index.html', {'Datas': Datas}, context_instance=RequestContext(request))
# Create your views here.
def temper(request):
      # temper = Data.objects(type_s = "temper")[0]
      for i in xrange(0,20):
        a[i]=Data.objects(type_s = "temper").order_by('-time')[i].data_s
      a0 = float(a[0])
      a1 = float(a[1])  
      a2 = float(a[2])
      a3 = float(a[3])
      a4 = float(a[4])
      a5 = float(a[5])
      a6 = float(a[6])
      a7 = float(a[7])
      a8 = float(a[8])
      a9 = float(a[9])
      a10 = float(a[10])
      a11 = float(a[11])
      a12 = float(a[12])
      a13 = float(a[13])
      a14 = float(a[14])
      a15 = float(a[15])
      a16 = float(a[16])
      a17 = float(a[17])
      a18 = float(a[18])
      a19 = float(a[19])

      template = 'temper.html'
      return render_to_response(template, locals())


def cpu(request):
      # temper = Data.objects(type_s = "temper")[0]
      for i in xrange(0,20):
        a[i]=Data.objects(type_s = "cpu_mem").order_by('-time')[i].data_s
      a0 = float(a[0].split(",")[0])
      a1 = float(a[1].split(",")[0])  
      a2 = float(a[2].split(",")[0])
      a3 = float(a[3].split(",")[0])
      a4 = float(a[4].split(",")[0])
      a5 = float(a[5].split(",")[0])
      a6 = float(a[6].split(",")[0])
      a7 = float(a[7].split(",")[0])
      a8 = float(a[8].split(",")[0])
      a9 = float(a[9].split(",")[0])
      a10 = float(a[10].split(",")[0])
      a11 = float(a[11].split(",")[0])
      a12 = float(a[12].split(",")[0])
      a13 = float(a[13].split(",")[0])
      a14 = float(a[14].split(",")[0])
      a15 = float(a[15].split(",")[0])
      a16 = float(a[16].split(",")[0])
      a17 = float(a[17].split(",")[0])
      a18 = float(a[18].split(",")[0])
      a19 = float(a[19].split(",")[0])

      b0 = float(a[0].split(",")[1])
      b1 = float(a[1].split(",")[1])  
      b2 = float(a[2].split(",")[1])
      b3 = float(a[3].split(",")[1])
      b4 = float(a[4].split(",")[1])
      b5 = float(a[5].split(",")[1])
      b6 = float(a[6].split(",")[1])
      b7 = float(a[7].split(",")[1])
      b8 = float(a[8].split(",")[1])
      b9 = float(a[9].split(",")[1])
      b10 = float(a[10].split(",")[1])
      b11 = float(a[11].split(",")[1])
      b12 = float(a[12].split(",")[1])
      b13 = float(a[13].split(",")[1])
      b14 = float(a[14].split(",")[1])
      b15 = float(a[15].split(",")[1])
      b16 = float(a[16].split(",")[1])
      b17 = float(a[17].split(",")[1])
      b18 = float(a[18].split(",")[1])
      b19 = float(a[19].split(",")[1])


      template = 'cpu.html'
      return render_to_response(template, locals())


def mem(request):
      # temper = Data.objects(type_s = "temper")[0]
      for i in xrange(0,20):
        a[i]=Data.objects(type_s = "cpu_mem").order_by('-time')[i].data_s
      a0 = float(a[0].split(",")[1])
      a1 = float(a[1].split(",")[1])  
      a2 = float(a[2].split(",")[1])
      a3 = float(a[3].split(",")[1])
      a4 = float(a[4].split(",")[1])
      a5 = float(a[5].split(",")[1])
      a6 = float(a[6].split(",")[1])
      a7 = float(a[7].split(",")[1])
      a8 = float(a[8].split(",")[1])
      a9 = float(a[9].split(",")[1])
      a10 = float(a[10].split(",")[1])
      a11 = float(a[11].split(",")[1])
      a12 = float(a[12].split(",")[1])
      a13 = float(a[13].split(",")[1])
      a14 = float(a[14].split(",")[1])
      a15 = float(a[15].split(",")[1])
      a16 = float(a[16].split(",")[1])
      a17 = float(a[17].split(",")[1])
      a18 = float(a[18].split(",")[1])
      a19 = float(a[19].split(",")[1])


      template = 'mem.html'
      return render_to_response(template, locals())