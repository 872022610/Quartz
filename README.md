项目名称<br>
>>Spring定时任务框架quartz
	
项目环境<br>
>>1.Spring Boot 2.* <br>
>>2.MySql 8.* <br>
>>3.JDK 1.8 <br>
>>4.mybatis <br>
	
项目说明 <br>
>>quartz是Spring的一个定时任务调度框架，通过它可以实现对任务的调度管理，同时也支持集群模式以及持久化，基本上能满足大部分的定时任务调度要求。这个也是我最近才接触到，相对来说研究的并不深，因此在这里只是暂时的写出了一个小的Demo，只包括了基本的持久化以及任务的开启关闭以及调度器状态的查询。quartz的持久化可以选择框架自己给出的数据库表，同时也可以自己设计表结构，在这个Demo里我是自己设计的表结构。<br>

>>1.quartz的相关概念介绍 <br>
>>>>  quartz主要有三个重要的概念:job、scheduler以及trigger。 <br>
>>>>  job也就是我们所写的一个个定时任务逻辑，每个定时任务的运行逻辑定义都是通过继承QuartzJobBean并重写executeInternal方法来完成的（也可以继承别的类，但是实际使用都一样）。 <br>
>>>>  scheduler则负责提供定时任务的运行环境以及负责对定时任务进行管理。Demo里面的schedule因为是交给Spring管理，因此整个项目的job实际上是放在同一个schedule里面的。 <br>
>>>>  trigger则负责任务什么时候触发，有多种指定方式，在Demo里面是通过corn表达式来指定触发时间的，如果对别的指定方式有兴趣，各位可以自己去网上找一下相关的资源。 <br>

>>2.框架相关的配置类 <br>
>>>>  quartz的相关配置类为config目录下的SchedulerConfig类与AutowiringSpringBeanJobFactory类。<br>
>>>>  SchedulerConfig主要包括JobFactory与SchedulerFactoryBean的配置，JobFactory为job类的工厂类，SchedulerFactoryBean则为调度器的工厂类，各位可以根据自己的需要来改写代码。AutowiringSpringBeanJobFactory则是负责将quartz的相关类交给Spring去管理，因为如果不交给Spring容器，是用不了Spring容器管理的各种Bean的。<br>

>>3.数据库设计 <br>
>>>>  因为只是个Demo，所以数据只设计了最基本的job表和parameter表，job表负责记录任务类的相关信息，parameter表则负责记录初始化任务时任务的各种参数。job_class_package_path与job_class_name记录了任务的类的路径，job_name和job_group_name是一个任务初始化时需要的参数，负责用来标记任务，相当于任务的别名。trigger_name以及trigger_group_name则是触发器的别名，name与group_name的组合在同一个调度器中必须唯一。 <br>

>>4.jobDetail与triggerDetail<br>
>>>>  预先定义好的job无法直接放入scheduler中运行，需要通过job预先创建一个jobDetail对象和triggerDetail对象，通过这两个对象才能在scheduler中操作相关任务，也就是说大多数情况下想要直接管理scheduler中的各种定时任务，必须通过jobDetail以及triggerDetail对象才能实现。 <br>
>>4.参数传递 <br>
>>>>  创建一个jobDetail类时，可以通过向jobDetail的JobDataMap放入参数的形式将参数传递给任务类。具体的步奏在Job4Demo3里面已经写好。 <br>
	 
接口说明 <br>

>>/quartzTaskOpen接口，通过传入的相关参数，创建jobDetail以及triggerDetail对象，将其放入scheduler中，若任务已经存在，则会抛出异常提示任务已经存在。 <br>

>>/quartzTaskClose，通过传入的相关参数，创建jobDetail对象并获取jobKey，如果jobKey在scheduler中关闭指定的任务，且若指定的任务不存在也不会报异常。

>>/initQuartzTask，获取数据库中的job信息，创建一个job列表，并开启这个列表中的job任务。 <br>

>>/getAllQuartzTask，通过向scheduler获取所有的jobGroupName并通过groupName获取jobKeySet,通过遍历set得到的jobKey即可以向scheduler获取所有正在运行的job信息。 <br>
	 
附言 <br>
>>因为工作原因自己最近开始接触许多第三方的框架，在这个过程中自己发现百度上的很多资料都是残缺甚至是错误的，明明实现这个框架需要注意的点有abc，而网上的很多资料则只会告诉你ab而不告诉你c，有的代码甚至都有问题。因为这种情况，自己走了很多弯路，浪费了很多时间。因此自己便萌生了将工作中用到的一些东西写成一个个小Demo放到github上，以此希望能够帮助看到的朋友，让大家少走一些弯路，少浪费一些时间，同时对自己来说这也是一次归纳和总结。 <br>
>>但是个人的能力和精力都有限，所以涉及到的相关知识点并不深。当然自己的本意也是先做出来一个能跑的小Demo，所以尽管如此，对自己来说这样做也非常的有意义。关于quartz还有很多可以学习的地方，它的功能远比我所提到的要强大，不管是出于个人爱好还是工作需要，在定时任务这一块它都值得大家去深究。 <br>
>>自己有意以后将所有的Demo都做成服务相互关联，所以在项目中也用到了Spring cloud + eureka + feign，如果大家不需要这些，可以自己将对应的依赖去掉即可。同时自己第一次在github上上传代码，难免很多地方都有遗漏和错误的点，希望大家发现问题后能够帮我指正出来，我们一起进步。自己接下来会写一个关于oauth2授权认证的demo，不过时间和精力有限，需要克服很多困难，只希望大家能够多多支持我这个小菜鸟给个小星星，谢谢！
	
