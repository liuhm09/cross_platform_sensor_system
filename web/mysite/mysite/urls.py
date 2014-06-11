from django.conf.urls import patterns, include, url
import settings
# from django.conf import setting
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'mysite.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
    #url(r'^admin/', include(admin.site.urls)),
    url(r'^$', 'mysite.sensor.views.index'),
    # (r'^site_media/(?P<path>.*)$','django.views.static.serve',{'document_root': settings.STATIC_PATH})
    (r'^site_media/(?P<path>.*)$','django.views.static.serve',{'document_root':settings.STATIC_PATH}),
    url(r'^temper/', 'mysite.sensor.views.temper'),
    url(r'^cpu/', 'mysite.sensor.views.cpu'),
    url(r'^mem/', 'mysite.sensor.views.mem'),
)
