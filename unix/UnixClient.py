#!/usr/bin/env python  

import urllib2
from httplib2 import Http
from urllib import urlencode
import sys  
import os  
  
import atexit  
import time  
  
import psutil  
  
print "Welcome,current system is",os.name," waiting..."  
time.sleep(1)  
   
line_num = 1  
  
def print_line(str):  
    print str  
      
#function of Get CPU State  
def getCPUstate(interval=1):  
    return (" CPU: " + str(psutil.cpu_percent(interval)) + "%")  
#function of Get Memory  
def getMemorystate():  
    phymem = psutil.phymem_usage()  
    buffers = getattr(psutil, 'phymem_buffers', lambda: 0)()  
    cached = getattr(psutil, 'cached_phymem', lambda: 0)()  
    used = phymem.total - (phymem.free + buffers + cached)  
    line = " Memory: %5s%% %6s/%s" % (  
        phymem.percent,  
        str(int(used / 1024 / 1024)) + "M",  
        str(int(phymem.total / 1024 / 1024)) + "M"  
    )     
    return line  
def bytes2human(n):  

    symbols = ('K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y')  
    prefix = {}  
    for i, s in enumerate(symbols):  
        prefix[s] = 1 << (i+1)*10  
    for s in reversed(symbols):  
        if n >= prefix[s]:  
            value = float(n) / prefix[s]  
            return '%.2f %s' % (value, s)  
    return '%.2f B' % (n)  
  
  
def poll(interval):  
    """Retrieve raw stats within an interval window."""  
    tot_before = psutil.network_io_counters()  
    pnic_before = psutil.network_io_counters(pernic=True)  
    # sleep some time  
    time.sleep(interval)  
    tot_after = psutil.network_io_counters()  
    pnic_after = psutil.network_io_counters(pernic=True)  
    # get cpu state  
    cpu_state = getCPUstate(interval)  
    # get memory  
    memory_state = getMemorystate()  
    return (tot_before, tot_after, pnic_before, pnic_after,cpu_state,memory_state)  
  
  
  
def refresh_window(tot_before, tot_after, pnic_before, pnic_after,cpu_state,memory_state):  
    os.system("clear")
    """Print stats on screen."""  
  
    print_line(time.asctime()+" | "+cpu_state+" | "+memory_state)
    #print current time #cpu state #memory  
    #print_line(str(int(time.time()*1000))+" | "+cpu_state[6:][:-1]+" | "+memory_state.split(" ")[3][:-1])

    print_line(" NetStates:")  
    print_line("total bytes:           sent: %-10s   received: %s" \
          % (bytes2human(tot_after.bytes_sent),  
             bytes2human(tot_after.bytes_recv))  
    )  
    print_line("total packets:         sent: %-10s   received: %s" \
          % (tot_after.packets_sent, tot_after.packets_recv)  
    )  
    h = Http()
    #data = dict(platform="UNIX",time=str(int(time.time()*1000)),type="cpu_mem",data=cpu_state[6:][:-1]+","+memory_state.split(" ")[3][:-1])
    data = 'http://202.112.51.64:8000?platform=UNIX&id=1&time='+str(int(time.time()*1000))+'&type=cpu_mem&data='+cpu_state[6:][:-1]+','+memory_state.split(" ")[3][:-1]
    
    #content = urllib2.urlopen('http://202.112.51.64:8000').read()
    resp,content = h.request(data, "GET")#, urlencode(data))
    resp
    time.sleep(1)  

    #data = dict(platform="UNIX",id="1",time=str(int(time.time()*1000)),type="network",data=str(tot_after.bytes_sent)+","+str(tot_after.bytes_recv)+","+str(tot_after.packets_sent)+","+str(tot_after.packets_recv))
    data = 'http://202.112.51.64:8000?platform=UNIX&id=1&time='+str(int(time.time()*1000))+'&type=network&data='+str(tot_after.bytes_sent)+","+str(tot_after.bytes_recv)+","+str(tot_after.packets_sent)+","+str(tot_after.packets_recv)
    resp,content = h.request(data, "GET")#, urlencode(data))
    resp
    #content = urllib2.urlopen(data).read()

    time.sleep(1)  
    # totals  

    

try:  
    interval = 0  
    while 1:
        args = poll(interval)  
        refresh_window(*args)  
        interval = 0.5
except (KeyboardInterrupt, SystemExit):  
    pass  