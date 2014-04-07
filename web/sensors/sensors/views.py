from django.http import HttpResponse

def sensors(request):
    return HttpResponse("Hello world sensors from django")