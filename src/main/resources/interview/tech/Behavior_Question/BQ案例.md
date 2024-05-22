## 1. Introduce

> Hello, I’m Jimmy. I’m a Senior Software engineer with a strong background in big data, distributed computing, concurrency, and cloud such as AWS.
>
> In my current role at BlackRock,  As data squad, I have developed robust and distributed backend RESTful APIs for Sales CRM data-driven solutions, ensuring high performance, scalability, and reliability. I aslo led 3 front end developers to buid up a full web applicationI. Additionally, I have led  data streaming process project  to inegrate the data from local to cloud projects by using Kafka, MessageQueue., I’m using Spark, HDFS, and python to built up data pipelines on Airflow for data migration into Snowflake.
>
> Previously I was working about 3 years at Vanguard Group, I led, designed and implemented large-scale distributed data ETL pipelines in AWS cloud for data migration to guide the firm’s investment strategies. We were using Scala, Spark, Python and AWS techs such as CloudFormation, EMR, EC2, Glue ETL and S3.
>
> I’m graduated from Strony Brook University, majored in Compute Science.  and I also own an AWS Cloud certification. Totally, I’m  prfessional  in Spring, Java, Python, Kafka, MessageQueue,  Big data techs, and SQL database such as MSSQL  and nosql like Cassandra.
>
> I'm excited about the opportunity and want to discuss how my skills and experiences align with the goals of the team. Thank you!

## 2. Questions

### Why leave current firm 

> I did many import projects  in the current postion to impact the firm’s sales. And I learn a lot from the team and firm. The only question is our entire division is not organized well and our report line is not stable. They are not evaluting the people  and outcome in correcct way. That is what I think.  And I think as a software engineer, I must keep learning, keep growing. I need to find a new oppoptunity to gain my knowledge and skills.



### Why Amazon

> Amazon is is a great company, leading the world in online shopping. Personally, I'm a big fan of Amazon's online shopping. I almot go shopping on line in each week. As a software engineer, every time I’m shopping on the Amazon, I’m aloways thinking how the appliction is running bechine the screen, how the data are processing in the flow. When I’m reading the job descriptoin,  I think I’m the right person matched with job description and aligns with team’s need. I have confidence to do my contribution.  And also, from personal technical perspective,  it will be a greate opportunity for me to learn form the team,  learn form the firm and grow up with the firm.



### **Tell me about a project that you are most proud of and the most challenging project you worked on**

- This aligns with "Deliver Results" (achieving successful outcomes on projects) and "Invent and Simplify" (finding innovative solutions to challenges).

  > **Situation:**
  >
  > The most challenging project(The project I’m most proud of)  I worked on is the one in current company. In last year, the firm was migrating our data from on local database into Azure Cloud. As client data squad to support sales, we need to migrate our portfolio, sales data into Azure dataverse . 
  >
  > **Task:**
  > We need to deliever the data within 2 months, otherwise, it will directly impact our firm’s sale and income. Since I have experince to with Kafka, Message queue, Java and Spring boot. I’m also very familiar with the data. So, I took the leadership with 2 junior engineer  for this project.  For me, at project level, it is the  first time to migrate data. There are couple of ambiguites we need to figure out. 
  >
  > 1. the security to call cloud api, 
  > 2. how to utilize the 3rd party cloud api to load the data
  > 3. how to convert the data type accross the MSSQL to cloud storage.
  > 4. The load strategy, batch size, retry times when we call the API. 
  >
  > 
  >
  > **Action:**
  > To tackle this challenge, I lead two junior application engineer to do deep dive analysis of the requirements, considering factors such as data volume we are going to store, the volume of user operations per seconds.   I listed all the important facors and buit a draft architecture.
  >
  > Then, I collaborated with cross-functional teams, including Microsoft cloud support team, our salse user , and product owners, to gather requirements and refine the application architecture. After that, I worked with pdm to create a development pland and believe we can archive it in two months.
  >
  > When I assign the work the my team mates, based on their expertise, I assign the infrature structure setup to one who is more famaily with it. and Assign the data interaction work to one who is good at the data part. I maily focus on loading data into the cloud by using the API.
  >
  > Throughout the project,  I also schedule daily meeting with our product owner to show the the process and milesone we archived.
  >
  > I encountered several challenges, including optimizing data processing algorithms to handle high data volumes and em beded with 3rd part API.  However, I approached these challenges with determination and creativity, leveraging my problem-solving skills and technical expertise to find innovative solutions.
  >
  > **Result:**
  > Finally, within two months, The real-time data streaming application was successfully deployed, . The application are able to handle a full load with  millions records  and hudread reocrd of live update per mins. It significantly become a standard for our coming data loading project.
  >
  > 
  >
  > Follow up:
  >
  > **Challenge**: The dedeline, Need to get this new server done in 2 months. I have couple of ambiguities need to figure out.  I did poc, make sure it works. And I also scheduled meeting with pdm, manager and prduct owener, deep dive into the details, then create a development plan to make sure we can make it.  In the development process, I also Schedule meeting with them per 2 days and give them update.
  >
  > **Conflict**: The conflict is we are trying to build the project in short time, but the users need to spend more time for additional data, since it’s need to be done in 2 months, so I talked with the product owner and pdm and manager. I made development plan of  phase 1 to for basic needs and phase 2 for additional things if they want.  Finally, I got it done.
  >
  > **ambiguity(模糊不清)**: 
  >
  > We are using a brand new 3rd party API to load the data, the deatails of the API are ambiguity. To figure out them,  I leveraged various resources such as offical documents and  developer communities to gain insights and knowledge about the API. Additionally, I buid up a research project, did the test by passing with different argumnents to call the api, , I tried similuate the process by using  production data  make sure we are reaching the limitation of the API without any erros.
  >
  > And I also scheduled daily meeting with the cloud support them to ask the questions that I encountered in my learnning.
  >
  > **Learn**: From technical perspective, I build knowledge to utilze the 3rd part api to load data into cloud, I can provide my experience to the team mate and do the consulting to other team. Form team lead perspective, I proved my leadership skills, I gain more experience to lead the team to deliver the result in a short time.

-----------------------------------------------------





## 3. Leadership Principle 

### 3.1 Customer Obsession - 一切以客户为中心

- **Leaders start with the customer and work backwards.** **They work vigorously to earn and keep customer trust.** **Although leaders pay attention to competitors, they obsess over customers.**

  领导者**聚焦客户**，并从客户需求开始，逆向努力，打造产品。他们**积极工作**，赢得并保持客户信任。尽管领导者会关心竞争者，**但他们对客户的关注度更高。**

- **考点**
  你的故事中是否体现了你能从客户的角度出发思考并解决问题。在这个层面上，面试官希望大家能抱有**同理心。**
  客户既可以是真正的end user，也可以是你下游consume你的data/signal/pipeline的team。

- **准备**
  回顾你的工作，你有没有为客户解决过什么问题？是你主动发现并且解决的？客户的反馈怎么样？你跟客户之间如何交流，交流的结果怎么样？. 

#### A time you used customer feedback to drive improvement 

-This relates with “Customer Obsession”

> **Situation:**
>
> **Task:**
>
> **Action:**
>
> **Result:**

#### Most difficult customer interaction 

-This relates with “Customer Obsession”

> **Situation:**
>
> **Task:**
>
> **Action:**
>
> **Result:**

#### A time where you had to balance the needs of the customer vs. the business 

-This relates with “Customer Obsession”

> **Situation:**
>
> **Task:**
>
> **Action:**
>
> **Result:**

### 3.2 Are Right, A Lot – 领导者要正确决策

- **Leaders are right a lot. They have strong judgment and good instincts. Theyseek diverse perspectives and work to disconfirm their beliefs.**

  领导者在大多数情况下都能做出**正确的决定**。他们拥有卓越的业务判断能力和敏锐的直觉。他们集思广益，并敢于纠正自己错误的信念。

- **考点**
  在信息不够且又必须做决定的情况下，你是如何做出正确的决定的？当时是否有死线压力，你如何从不同角度收集重要信息，怎么做出相对明智的决定？

- **准备**
  回想一下你工作当中是否有过必须要做决定但是又没有足够信息的情况，你是怎么做的？你为什么做了那个决定？虽然没有足够信息，你如何确定那些信息是做决定必须的，那些是不必须的，你如何收集那些必要的信息的？

#### A time you made a difficult decision and how you knew it was the right decision. 

#### A time you made a decision without data 

#### A time you made a bad decision and how you learned from it 

### 3.3 Ownership – 主人翁精神

- **Leaders are owners. They think long term and don’t sacrifice long-term value for short-term results. They act on behalf of the entire company, beyond just their own team. They never say "that's not my job."**

  领导者愿意**承担责任**。他们从长远考虑，**不会为了短期结果而牺牲长期价值。**他们代表整个公司行事，而不仅仅是他们自己的团队。**他们从不说 “这不是我的工作”**

- **考点**
  
  - 这个LP非常straightforward，就是考察你对日常工作的负责任和态度，不论这个工作是不是你的work，你都乐意dive in，发现问题，尝试解决问题。
  - 还有就是你知道how to trade-off between short-term gain and long-term gain。
  - 通常你需要讲一个在你team/scope/responsibility之外的故事，并且一定要重点强调，这个事情为什么非要由你而不是owner来做（比如当时事态紧急，owner又休假了，所以你来顶上了）。
  - 最后你要focus on impact，量化impact。
  
- **准备**
  回想一下你过去跟别的team一起collaborate ship一个大feature的时候，有没有需要你顶上去修别的team bug的情况？或者你自己发现了别的team的feature的dsat，你如何dive in，分析问题，最后解决的？ 

####  A time you stepped outside of job scope or responsibility and solved a problem

- This relates to "Ownership" (taking initiative to solve problems regardless of job boundaries) and "Bias for Action" (taking proactive steps to address issues).

> **Situation:**
> When I was a owner of backend applications, our application is providing the sales data into our client. Normally, when user check the data, they first need to login ,then there is permission server will check the user’s permissions, finally the user can see the data provided by my applicaiton. Sometimes, our sales may report the issue that they can not see some data that they are supposed to see it in prod. Then They will raise the ticket. Product manager and QA team then will try to simulate same scenario. First thing, they need to go to the database to run multiple sql query to get same permissions  as the sales user then insert into permission table.  Normally they need to take about 10 mins to finish it. And als sometime, many developers, including me also need to take the same step to test some sales data. 
>
> **Task:**
>
> I realized that I can create a simple Web API to handle this situation for our team. I may can help people save a lot of time when they clone the users.
>
> **Action:**
> After that, I use my personal free time to make a search on the process. I found it’s doable.  This project may help the team to save a lot of time. I then meet with my manager, explained my idea and what I have resarched. My manager is very happy with my insigth on this situation. With my manager’s support, then I meet with our product manger team and also the QA team, deep dive with them to understand process when they clone the users. I tried to find all the edage case I need to cover. after that, with my manager’s support, I gain some time to develop this simple project. After about one week, I build up s simple web api to handle the clone request. 
>
> **Result:**
> After I build up this simple clone project, we have 4 product manager and 12 developers are  using this API to clone the permissions. The whole team saved about 8 hours per month. I know this is not my responsibility to help the product manger save their time. But I’m glad that I can help the team. This also impove my insight on new project, I also learn how to developped a project from user’s perspective. 

#### **A time you had to sacrifice short-term gains for long-term goals**

- This aligns with "Think Big" and "Ownership" (making decisions that prioritize long-term success over short-term gains).

> **Situation:**
> In a previous CRM  project when we are working with client, which is our Sales team. They proposed that they want to see some sales reports to show the total fund/portfolio/product they have sold in the year based on the team and territory/area. And also they want to see the percentage they finished based on the sales goal of year. The project need to generate muliplte aggreations report and showing the data in the UI. 
>
> **Task:**
> It is an important because stakeholder can track the reports and adjust the sales strategy based on the report. I was assigned this project and led one junior engieere to get it done ASAP. 
>
> **Action:**
>
> After I got assigned, I immeditaly hold a meeting with our stakeholder, our Product manager and team mate to discuss the requirements and all data we need. After meeting I lead the developers then start research on the data. After about 2 days, I found all sales data we need are all sitting on the SQL server.
>
> I know the quickest solution is we can build up sql procedures on the SQL server and run them to get the result. 
> The Advantages is that the development will be done quickly in one month. And our client will see the data soon.
> But there are some disAdvantages.
>
> 1. It’s hard to deubug and matainence with sql procedure.
> 2. and with the increment of sales data, the sql procedure will need more resource to compute day by day, this directly  will imapct the overall performance of the database.
>
> Another solution is we can pull the data into our HDFS system, then we run the spark job to execution all of reports, then put back into Database. This solution can significantly resolve all issues we found in the previous sql procedure solution. But I may need 3  more weeks to  build up the data pipeline.
>
>  As a lead, I know the stakeholder and saleas want to see the data ASAP. But from a long term gains perspective, I think I need to build up a roburst, stable project without any potentinal issues. Even the project need 3 more weeks, but the team will save a lot of time to maintaince the project in near future. and the project will not impact the SQL server perfermance too much. After I relized it. I scheduled a meeting with the Product manager, stakeholder , the manager and teammater. In the meeting. I listed the two solutions with all the advantage and disadvantage. And I proposed the data pipeline solution, and explained the details why I perfer with the pipeline solution. Finally, the whole team both think my suggestion is reasonable and doable. After meeting, I immediatly work with the product manger to make a detailed development plan for each step. Then I led another developer and started working on the project.
>
> 
>
> **Result:**
> Finally, after one month and two more weeks. My team built up a high perfermance data etl pipeline to gererate couple sale reports daily and bring  them to our clients and users. The sales manager can keep tracking the sales on each terriotiry and adjust the people of each team based on their sales. Sales team can see the percentage they have finished.  And also at a higher level. Directors  can see which product are more popular to our client.
>
> From my personal perspective, I led team to finish the project one week eailier then the date I promised. Even the deveopment is longer then the short term solution, But the project is more robust, stable and eaisery to maintance.
>
> I also learned more experience to make trade off  and decision between short term gain and long term again. And the project also educate me how to lead the project and team to make correct decision and directions.

#### A time you saw a peer struggling and decided to step in and help 

> **Situation:**
>
> When I was working at Vanguard. I’m mentoring a Junor software developer. One time in the 1: 1 meeting , he told me he was curently doing a new project and need to use the CloudFormation to create AWS S3 and grant different access at different sub folder. That is first time he used the cloudformation to config the S3. 
>
> **Task:**
>
> Aftre one weeks in another meeting, I pinged him to ask the update on the new project to see if anything I can hlep. He told me he has some issue with the configuration, he could not grant the user with different level and he has tried to resoluve the issue by one week. I kned he has less experience to use the CF. since I have the experience with CloudFomation.
>
> **Action:**
>
> So I scheduleed a meting with him, ask the basic requestment of the project and figure out what he was trying to do. Then I paire programming with him. First of all, I give some basic definitions in cloutformation, so he would know what is the meaing for each element from a high leve. then I with my help, I created one example, after that, he follow it then finish the rest of them.  I also give him some of internal WIKI page to show the standard we are following in creating the CF.  I also suggestion he can take a  AWS certification. 
>
> **Result:**
>
> Finally, after 2 days, he created the cloudFormation and be able create the S3 and grant access to different group. He finished his taks as planned.   And he got the certifaciotn after 2 months. And also from my personal perspective. I improve the mentorship.And I’m glad that I can step in and offer my suggestion to help him be successful. 

### 3.4 Bias For Action – 崇尚行动

- **Speed matters in business. Many decisions and actions are reversible and do not need extensive study. We value calculated risk taking.**

  商业中速度非常重要。许多决策和行动是可逆的，不需要大量的研究。我们需要计算风险评估。

- **考点**

  - 你需要在了解potential risks的情况下迅速行动，fast iteration来验证是否可行。
  - 你需要明确知道有那些risk，有哪些是冒险是值得的，能够带来更高的ROI（return of investment）。
  - 如果consensus不能被reach，你了解了风险并且准备action，要强调你的solution是reversible的，不会对production产生影响（这一点非常重要）。

- **准备**
  想一想你工作过程中，有哪些情况下你和peers不能达成一致，你怎么了解你的solution可能带来的哪些风险，你如果fast iteration来验证的？你怎么保证你的solution不会对prod产生影响？是通过什么方式跟peer达成一致，ship你的feature的？

### 3.5 Think Big – 大格局

- **Thinking small is a self-fulfilling prophecy. Leaders create and communicate a bold direction that inspires results. They think differently and look around corners for ways to serve customers.**

  小格局是一个自证预言。领导者创造并传递一个能带来成果的大目标。他们有与众不同的想法，他们为服务客户而到处寻找方法。

- **考点**

  - 你是否能“挖坑”，create something new？你提出的新想法能否有足够大的impact，来drive business和serve customer。
  - think big，但是implement要着眼于小处，先从简单的lower hanging fruit 开始做起来。采用divide & conquer的方式来实现你的大目标。

- **准备**
  回想一下你做过的比较大和复杂的项目，它们都有哪些big/audicious goal? 刚开始的需求是否vague，你怎么break down vagueness的，如何plan你的项目，每一步骤都如何实现，怎么divide & conquer的？刚开始有哪些lower hanging fruit，后来怎么逐步完善项目并且朝着你的“big”目标前进的？

#### A time of a radical approach you proposed to solve a big problem 

- This relates to "Think big”

  > **Situation:**
  >
  > When  I was working in Vanguard, there is a project that I need to work with team to build a data ETL pipeline and generated couple of report to support invesment team. These reports are based on 20 tables and then join with each other, finaly aggrations on the data to generate the report. The team has a lot of experience to build up data ETL pipelin by uisng Spark on HDFS. 
  >
  > **Task:**
  >
  > So when they started the project, they choosed use the sqoop, spark and S3 to build up the pipeline. Since there are a lot of join and aggregation on the data set. and the spark is a distributed computing framework. So the data join and aggration cause a lot data shuffle and data balance issue. Finally it cause out of memorry issue. The team were focued on optimizeation to solve the memory issue. We spent a bout month to and could not got to the final step. 
  >
  > **Action:**
  >
  > I was on the daily meeting with them. I know the issue and the project was delay to deliver. Then Aftrer I analyzed the total dataset . I know most of them are all small data set. but they need a lot of join and aggreation. I have some experince with the Pandas, which is a python lib used for computing in memory. Then I think maybe I can use the Pandas to do the same things. Then I scheduled a meeting with the team and manger, I pointed out that based on total size of data, Pandas maybe a better solutioin , we don’t need worry about the data shuffle and rebalance issue. The original  spark soultion is better with big data. If we use it, the develiver could be delay cause we need a lot of time to resolve the memory issue. The team and manager are both agreed we switiched to Pandas solution. After meeting. I imemidatly working with the team mater who is familiar the business logic on tjhe data. We pair projgramming together to speed up the development process.
  >
  > **Result:**
  >
  > Finally, after 2 weeks, we deliver the report to our client. Invesment team can see the daily report by our solution.There is no memorry issue with the project. For the team, we all learned a new way to handle the small data set procesing and know how to pick up the solution by valiating the data set. From my personal perspective. I also learn how to Think big in the project, how to approve my idea. and How to work with the team to change our status.

#### A time when you took a big risk and it failed 

- This relates to "Think big”

  > **Situation:**
  > In my current postion in blackrock. THere is one task  I’m taking to update update our database account in the jdbc connections. This project has multiple servers and connect with 3 different database. one is SQL server, one is Sybase, another one is our Cansandra. 
  >
  > **Task:**
  > After I accept the taks, I imemditaly start my works. after update each account, I did end to end test, make sure server can connect with the databvase and be able to query the data. I finisheh it quickly. Then merged the code with main and then I found my teammate is also trying to release. I was planinngt orelease it separatly, since that’s first time I run the server in prod with new account and these server are pretty important and impacting a sale process. But we only have one releas window in that week. So I took. the risk and think it should be ok. After relase then, I found I could not start the server.
  >
  > **Action:**
  > After that,  I checked the log and could not find reason in a very shot time. And our prodcut server is down in prod. I immidtaly call my team mate, gave the update and he could not figure out the issue. So I immeditaly created another release branch and roll back to the previous stable version. Then I worked with the teammate. carefully check the log, finally  I found with his change, he is using new account as fitler to quety the data then cause the issue. After I added that user into the table. The issue was gone.
  >
  > **Result:**
  >
  > After one week, I fully tested my code and also inclue his change. then made a successful release. THe new feature was released into the prod, and the server are using new account to connect with the database. I also learned, even it’s a narrow relase window, if I relase with some other’s change. I need do another test with all changes. Cause this projectg is very important project. Stable is the highest prioprity. 



### 3.6 Invent & Simplify – 创新和简化

- **Leaders expect and require innovation and invention from their teams and always find ways to simplify. They are externally aware, look for new ideas from everywhere, and are not limited by “not invented here." As we do new things, we accept that we may be misunderstood for long periods of time.**

  领导者期望并要求他们的团队**保持创新动力**，并总是找到**简化问题的方法**。**他们从各处寻找新的想法**，而不仅仅限于公司内部。当然，在做任何新的事情的时候，他们也准备接受可能被外界长期误解的事实。

- **考点**

  - 这个LP就是由两部分组成的，一个是invent，一个simplify。
  - invent要求你能够创新，比如你开发了工具大大简化了debug的流程，或者你参与研发了一个系统提升了用户体验，最终发了文章或专利。
  - simplify其实就是你怎么用existing solution来简化你的开发流程，比如你需要一个系统，并且知道这个系统已经有一个partner team做了，你就需要跟partner team聊，表明你的需求，看看能不能collaborate把这个系统integrate到你的work里。
  - 不论是invent还是simplify，你的solution必须是scalable且long-term的，你要时刻记住，你要long-term gain driven，short-term的hack不能做。

- **准备**

  - invent：你是否提出了什么比较创新的idea来解决问题？你开发了什么工具大大简化了开发流程？你有没有什么研发经历？
  - simplify：你怎么跟partner team合作来一起work on一个项目的？你当时有什么需求？你为什么不能自己弄一个？你怎么知道哪个partner team有existing solution？你怎么跟partner team initialize communication的？你怎么和partner team collaborate的？最后你的项目因为这些collaboration是否按时或提前上线了，整个solution是scalable且long-term的吗？

#### The most innovative thing you’ve done and why you think it’s innovative 

- This relates with “Invent & Simplify”

  > **Situation:**
  >
  > When I was working in vangarud, we are running data etl pipeline to generate the salse report, Then our business investment team will consume our data. THe pipleine must make sure it generate the data accruate. In the project, there are many process to log the details so we can track the process and data.
  >
  > **Task:**
  >
  > I was working with team to track the log to make sure data are correct. Each time, I need run multiple log query to get the result. And it took about 15 mins  to finish. then I think we should put this step in our data pipeline process to generate data report to show the total record we pulled from the datasource. and also do some aggreationis based on our finnal data and compare with source data, If we add this step in our pipelin, it will run faster and we can add more checking. 
  >
  > **Action:**
  >
  > I brought my idea and talk with the team, I got the support form him, then I stated the deveopemnt this checking step and put in our code as last step.
  >
  > **Result:**
  >
  > After 2 weeks, I got the developemtn done. the project run this step as last step and got it done in 5 mins. The result was loaded into a talbe, so the team can track it everyday on the stand up meeitng to make sure no issues on it. With this time development, I got more familar with the data, and help the team saved develpemnt time.

#### A time where you solved a complex problem with a simple solution 

- This relates with “Invent & Simplify”

  > **Situation:**
  > Perviously, there is a project,that I sync data into the clould from local database, in project is calling the api of all local and then sent the data into cloud. after a long time , I found process is slower, since i’m the owner of it,  I need to optimize it.
  >
  > **Task:**
  > I started tracking the step that    when application call the api into the process, I found it’s too slow and take 2 hours in weekend to do a full load. pervious it take haft hour, but data is increasing,  it’s a sequential api call tha by using the pagination. and in each call ,we already hit the limit of each api call. So  I know this way will not wokr, I need to find another way to do it. So I shceduled call with the api owner, after that they told me that they also have the api to call the the id, 
  >
  > **Action:**
  >
  > After that , i creat the jdbc coneciton to db, then pull all ids ,then use the concurrent hash map and future task to call the api, each call will take 500 recores, but in the same time , we cna call the api 5 times, 
  >
  > **Result:**
  >
  > finally, the wole proces only took 10  mins to get done, and we changed the weeklly load into daily load in the night ,it imprve the load speed and also imporve the data accurate since we can run full load in daily. I also learned thta tto deviver the resulte, I need to communicate wit the team ,and keep thinking to find a better soluionts.

#### A creative idea you had that ended up being difficult to implement 

- This relates with “Invent & Simplify”

  > **Situation:**
  >
  > **Task:**
  >
  > **Action:**
  >
  > **Result:**

### 3.7 Learn, and Be Curious – **求知与好奇心**

- **Leaders are never done learning and always seek to improve themselves. They are curious about new possibilities and act to explore them.**

  领导者永远不会停止学习，并总是寻求提高自己。他们对新的可能性充满好奇，并采取行动去探索它们。

- **考点**

  - 考察你平时是否在持续学习。我觉得这个LP是非常容易讲的。你在工作中肯定是需要学习tool，学习别人的工作，了解不同team的工作，看文档等等。工作之余你会读论文，打比赛，做一些side project，或者读别的公司的tech blog，学习系统设计，等等。
  - 重要考察的是，你如何用你已经学习的新东西应用到你的工作上，并且产生了比较大的impact。你需要思考有没有类似的例子。如果没有，那你就想一想你的工作中有没有什么东西，可以利用你自己学习的东西来improve，比如你发现项目的一些系统设计得不是很合理，你自己学习了系统设计之后发现这部分可以进一步提高，然后你就动手做了，结果果然性能提高了，之类的.

- **准备**

  - 思考一下你是如何保持学习的，你都学习了什么具体内容，你怎么把你学到的东西应用在工作中的，产生了怎么样的impact

### 3.8 Dive Deep – 刨根问底

- Leaders operate at all levels, stay connected to the details, audit frequently, and are skeptical when metrics and anecdotes differ. No task is beneath them.

  领导者深入各个环节，随时掌控细节，并经常进行审核。不遗漏任何工作。数据才是最重要的。当数据和感觉不一致时，可以质疑，但要相信数据。

- **考点**

  - 回答时要强调系统的复杂性，deep dive过程中你会需要make一些assumption，它们都是educated guess，而不是瞎猜。在deep dive的过程中，你是follow一个一般化的procedure的，你要structurize all the possibilities, 然后focus on impact.

- **准备**

  - 想一想你平时有没有debug一些复杂系统的经验，我觉得只要你debug过程中，涉及到了3个component就算是比较复杂的系统了。有哪些并不straightforward的问题，你需要控制变量法，或者take educated guess来寻找debug的方向，然后deep dive下去解决问题，问题解决完了之后有哪些impact？

#### A  time you were trying to understand a problem on your team and had to go down several layers to figure it out 

- This related with Dive Deep

  > **Situation:**
  > In blackrock, we have CRM project. Salse people can use it to sale our fund and portfoilo to our client and track the process. It’s impaortant project . So, We have QA team will check all process before we release into prod to make sure there is no issue with current logic. IF the QA team want to see the same data as sales see, they need to add themself into same permisison group, then QA will have the same permission to view the data. afte done, he will remove from the group.
  > One day the one of QA raised a ticket that He could not saw the sales data on the webpage.
  >
  > **Task:**
  >  I took the task and need to figure out which step is wrong. THe salse data was generated in sql server, then pushed out to the Cansandra NoSQL data base daily, then backend api pull the data if this user has permission to view the data.
  >
  > **Action:**
  >
  > First reason I’m think it might be the API lost the datat, So I tried to use the same userid to call the API, then I found I’m not able to pull data. Then I thought data was not in Cansandra database, then  I went to the Cansandra database to query but  I f ound the data was there. but the user was not in the permsion group of that record. that’s why he could not see the data. But I know the QA team did add himself into the group. Since I know these data are generated in our sql server, So I went to to the Task scheduler, then I found that the data was generated in the morning 2 AM, then the QA add himself in the 10AM ,which means , he can only see the record in next day when the sql server process rerun.
  >
  > **Result:**
  >
  > Finally , I realized that issue was about the operation time, then I talked with him ,after he added himself into group, then I rerun the sql process to generated a new report, then push. to Cassandra, then He is able to view the data. with that time debug. I got more familar with the project and also the bussines opetaions.

#### A time you linked two or more problems together an identified an underlying issue 

- This related with Dive Deep

  > **Situation:**
  > In blackrock, I was leading two engineer to develop a web application. This web application provide an UI interface to enable peopel set up the meta data of the pipeline, so with our any experinece with coding, people can eaisy create a pipeline running on the airfow. behined the secne. the backend store the meta data info into the sql database. the project has been released into prod env and people are using it. One day, our use told me in a meeting that, when he create a task with description. after he saved, and re open it, the description was cutting off. I immidtaly check the table schema we defined in the database, it has enough space to store the value. i though that might be a misstake. After two weeks, anther person told me the same issue.  
  >
  > **Task:**
  >
  > Befoe that, we have never heard the complain about it. then I thought there might be a problem in the code or some where 
  >
  > **Action:**
  > I then created a ticket for it to figure out. I created a simlar task with a very long description. then hit save and  Istart ed debug it. I checked the code. I saw the entrier descrioptn value are passed without cutting off before sent to the database. The last step is that the backedn call a sql procedure stored in the database to insert the value.  I know we have enough space for the descripotion value. So there might be something wrong with the sql. after that I relazied I should check. when I open the sql procedure. I found when we receive the vlaue passed by backedn, the descripton length we defined  was smaller then the talbe schema one. After I found the issue. I immediatly change the code. and also check all ohters sql procedure to make sure there is no potential issue for this.
  >
  > **Result:**
  >
  > Finally, within 2 days, I fix it,  after i changed the code, the user is able to create task description without any cutting off isuee. and I also learned, I should carelully listen to our users and pay attention on it, cause their feedback is the best way to optimize our project.

#### A specific metric you have used to identify a need for a change in your department 

- This related with Dive Deep

  > **Situation:**
  > When I was working in Vanguard, we are using the EMR which is runing on EC2 for our data pipeline.  We have a project to runing on it and pull data from oracle process them and then load into S3.
  >
  > **Task:**
  > I was assign the task to track the performance of EC2 cluster to see it we can optimze the config.
  >
  > **Action:**
  >
  > Then I go to the EMR console, tracke ec2 status of  most 3 months. I inspaire the cpu useage, memorrage usage and disk usage. Then I fould our have a high confg on the memorry and disk, which means we could lower the config to save some money. I track the 3 months history, then most of them are using half of the limit. THen I schedule a meeting wiht manage and teammate, I show the metic I was checkign. the suggested tghat we can lower down the config to save some cost. the team agreeon it. then I worked with team mate, lower down the conifg, keep runnign couple time to make sure we have enought usage, And I keep track the EC2 metric to make sure we are on a good spot. 
  >
  > **Result:**
  >
  > Finally we lower our config, and it saved about 200 dolloares monthly. which is that we can save 2500 in one year. I also got more familar to track the prefomance metric and adjustt our config based on it.

### 3.9 Insist On The Highest Standards – 坚持最高标准

- Leaders have relentlessly high standards — many people may think these standards are unreasonably high. Leaders are continually raising the bar and drive their teams to deliver high quality products, services, and processes. Leaders ensure that defects do not get sent down the line and that problems are fixed so they stay fixed.

  领导者有着坚持不懈的高标准——许多人可能认为这些标准高得不合理。领导者不断提高标准，推动他们的团队提供高质量的产品、服务和流程。领导者保持关注直到问题解决，以确保缺陷不会被传递到生产线上。

- **考点**

  - 考察你如何set and achieve goals that are challenging, and solve it realistically.
  - 拆解为，首先你的goal是什么， 它有多challenging，你有什么计划能够achieve this goal，你的计划有哪几步，每个步骤设置的realistic吗？

- **准备**

  - 想一想你工作中的big project，complex project，你是怎么tackle这些project的，你给这些project定制的metrics是realistic还是audacious？你怎么achieve这些metrics的？怎么拆解项目变成小component的？

#### A time you had to change the status quo, how you change it

- This aligns with "Insists on Highest Standards”, Think Big" (challenging existing norms and driving innovation) and "Dive Deep" (questioning assumptions and seeking improvements).

  > **Situation:**
  > When i joined the blackrock. I was assign a task to build a simple spark etl data process. Since I need to run the pyspark in HDFS enviorment. My manager told me I can use pycharm and remotly  connect with the HDFS system. I fould it’s complicated  and I also need to use the ultimze IDE versions, which need to pay about 600 for license.  
  >
  > **Task:**
  > I have 3 years experience for data pipelien and I have experience to similuate the hdfs in my local. So I ask my manger. I can try to set up it on my local. Then I can use the jupyternotebook to start the developemnt and easily debug.the.process. 
  >
  > **Action:**
  > My manager agreed on it. Then I started on the research. I figure out security issue to connect  with the firm’s database. Then I setup the jupyternotebook, and also the hdfs env on my local. It tooks about 2 days to get all evn set up. After that I’m able to start development. I aslo created a WIKI page, so our teamater can follow the same steps on it.
  >
  > **Result:**
  > I finallly finish my data etl pipeline on my local. and release the pipeline into the product enviorment. I also save my time to debug caues with jupytenote book ,I can run the code line by line. But with Pycharm, I need to wait time to start the server, then click the step one by one, which waste some developemnt time.  And  I don’t need a untimate version IDE and save 600 for the team. All teammate are pretty happy that they can use a new way to work on data etl pipeline process. I also got farmiar with the firm’s security enviorment(like connecitn with database, how to set up the proxy).  

#### Tell me the most significant improvement project you’ve lead 

- This aligns with “Insists on Highest Standards”

  > **Situation:**
  >
  > When I joined Blackrock, there is a legacy project , which will generate couple of sales reports based on the sales data on the Sql server. The project is using the sql procedure solution , the process is running directly on the SQL Server. It’s a quick  solution because all data set are sitting on Sal Server and we can directly use it. But with the accumulation of sales data, the process become slower and slower and it occupied a lot compute resource on SQL server. 
  >
  > **Task:**
  >
  > I have experince working with data pipeline. The project was assign to me to lead another junior engineer to  faster the procss。 
  >
  > **Action:**
  >
  > When I assign the project. I met with the precious project owner and client to understand the process logic and business logic behind the scene. After the meeting , I realize that optimizing the sql proceude could not be the best solution. I may deliever the project eralier, but it could not resole the slow procesing issue from a long term. I made a decision that I shoud convert these sql soution to a big data soluiton and runing the process on the Airflow. The first step I working with team mate to fully understand the data that we need, and the logic to gernerate the report. Then in the next step, by following the sql procedure. I create a process by using python and pysppark. and comparing the result between them. 
  >
  > after that I think it’s doable.  After that I worked with product manger to make a very detial development plan. I also presetn my solution archetecture to the whole team and get it approved. After that, I assign the two sql - python conversion taks the teammate. And I took the rest two tasks. In the mean time, we have daily stand up meeting to show the step where we are . and I scheduled weeksly meeting with product owner to show the process we are in.
  >
  > **Result:**
  >
  > After 6 weeks, I build up the data etl pipeline. The result of pipeline are matched with the version of SQL procedure . The sql procedure takes about one hour to finish the porcess,  But the new pipelien process only take 20 mins in parallel. This project has become a standard to convert the SQl procedure to spark version. It approved the this solution is doable and more effcient and also not hold the sql server compuation resources. I also approve my insight on this project and gain more leadership during the development. I learned how to  codsider the project on a higher leeve and how to think big and follow it and mke it. 

#### How do you seek out feedback on performance? 

- This aligns with “Insists on Highest Standards”

  > **Situation:**
  > When I joined blackrock first year. I’m new to the team.
  >
  > **Task:**
  >  I need to get familare with our current projects. And build a good relationship with the teammate.
  >
  > **Action:**
  > To help myself quicly on obarding, I used the 1: 1 time meeting with my manger , I asked my manager what I should focus on in the next month, and what is. the deveopmnt plan at high leve. and also our neighbor team waht they are working . I aslo meet with my teammate weekly. ask for the suggestion for my current work , how I can improve my performance. Is there anyting I can help them. For the suggestion from  my manager and temamater. I carefully list them down. Deinfied a plan to improve them. I also schedule meeting with them to ask the  how is the change i made. I also check with the PDm and stakeholder, to ask if anything they unsatifaiy and thing I can make it better.
  >
  > **Result:**
  > WIth a good connectiosn with my manger, and teammater, I keep learning, impove my peformance. I got a pretty high performance in hte end the year. Which encouage me to keep moving on in the next year. I aslo learnning how to seek feedback with the team mate and people I collablrate . How I shoudl take the suggestion, defined a plan and improved it.

### 3.10 Earn Trust – 获取信任

- Leaders listen attentively, speak candidly, and treat others respectfully. They are vocally self-critical, even when doing so is awkward or embarrassing. Leaders do not believe their or their team’s body odor smells of perfume. They benchmark themselves and their teams against the best.

  领导者认真聆听，坦率发言，尊重他人。即使让自己尴尬和窘迫，他们也会坦率的自我批评。领导者并不认为自己和自己的团队是永远正确的。他们向最好的团队对齐，并以此来检测自己和自己的团队。

- **考点**

  - 你肯定是要赢得别人的信任，所以这个LP考察的是你interpersonal skills，重点是考察你如何跟别人behavior和communicate。
  - 沟通是重点，遇到问题的时候，一定要有full picture，能够理解双方立场（要有同理心），知道问题从何而来，考虑到各自立场的pros and cons，以及提出plan来resolve conflict。

- **准备**

  - 你工作中肯定遇到过一些项目的讨论，有时候就是会有一些conflict的。比如有些时候是优先级的conflict，有些时候是metrics的conflict，有些时候是expectation的conflict，等等。想想你是如何处理这些conflict从而赢得信任的，你是如何resolve别人的concern的

#### A time you significantly contributed to improving morale and productivity on your team 

- This relates with “Earn trust”

  > **Situation:**
  >
  > **Task:**
  >
  > **Action:**
  >
  > **Result:**

#### 3 things you’re working on to improve overall effectiveness 

- This relates with “Earn trust”

  > **Situation:**
  >
  > **Task:**
  >
  > **Action:**
  >
  > **Result:**

#### A time you received tough or critical feedback 

- This relates with “Earn trust”

  > **Situation:**
  >
  > **Task:**
  >
  > **Action:**
  >
  > **Result:**

### 3.11 Deliver Results – 达成业绩

- Leaders focus on the key inputs for their business and deliver them with the right quality and in a timely fashion. Despite setbacks, they rise to the occasion and never settle.

  领导者会关注其业务的关键决定条件，确保工作质量并及时完成。尽管遭受挫折，领导者依然勇于面对挑战，从不气馁。永远不要轻易屈从或妥协于任何事情，直到最终获得最好的结果。

- **考点**

  - 你的故事要体现你在面对挑战和不确定的时候，如何unblock你自己，并且deliver high quality work的。

- **准备**

  - 我们每个人都完成过项目，你回想这些项目从plan到deliver的整个过程中，遇到了哪些挫折和挑战，你是如何unblock你自己的？

#### A time you were driving toward a goal and realized more than half way in that it may not be the best goal 

- This relates with “Deliver Results”

> **Situation:**
> When I’m in blackrock, one time, there is one samll project assign to me. We have a legacy project that running in sql server, we need to convert it int pysthon and pyspark version in hdfs.
>
> **Task:**
> The project is convert the sql procedure into a spark  hive version. these four sql procedure process based on the sales data to generate the sales report.
>
> **Action:**
> After i took the action, I sitting with the legacy owner, talk the details, make sure understand it. Then I thought I can finish it in oen month. after meeting, I start creat the tickets then start working on it. When I ‘m working on it, There ar a lot of join on the sql procedure to generate the data step by step, so I need to make sure on each step, I got the same result like t he sq procedure. I’ mruning everything in tst, somtimes I found the result are different, somre of reasons are the logic are different, and some of reasons is some one changed sale data. after one month, I only got half of them done.
>
> **Result:**
>  I talked with the manager, I admit I can not get it done. I need one guys to works with me. finnaly I got it done in two month. I leanred how to update the goal ,and how to ask for help if I’m in the trouble, and how to resovle the blockers.

#### A goal you set that took a long time to achieve/are still working towards 

- This relates with “Deliver Results”

> **Situation:**
> we are having a new dirstribute computing platform, the project is using airflow and runign process on hdfs. 
>
> **Task:**
> there are alot of legacy system, in blackrock, like FTP server to load data into hdfs, sql process to generat the report,
>  spring batch process running with cron jobs,  I need to migrate the prcess to make sure they can run on afirow.
>
> **Action:**
> This is long project, In the beginning , I work with team, bsed on the priority, the project is dviedned into 4 phase, each phase will take half year, in befingning, I took the respornbilit to build the utilitys funcitons to makse sure we can support all the system,  sql, bash, python, .  we have the memting twick in one week, give theu update make usre we are not delay on each process.
>
> **Result:**
> we have done haof of the process. and still working on it. with new airflow platform , it’s eality to manage all computign procsse, and process is runing faster on hdfs with spark,  I also learned how to dilever the reslt if it’s too big, and how to arrange the prioprit byu working with teammate.

#### A time you delivered a project under a tight deadline 

This relates with “Deliver Results”

> **Situation:**
> Recently, I need to work with another two senior devlopers to sync data from azure clound into our snowlake, this is a poc story that I need to make sure that the soulution is doable and we need to presnet the process in the 3 weeks,
>
> **Task:**
> When I got asisgned, I immeditally scheduled meeting with antoehr two developers, in meeitng, I sugegest , since the process  need to pull data from the azure api, and load the data into the process, I’m good with api, so I took make sure that I ‘m albe to call api to pull the data, deveoper A will clean the data, the developer b will create the metadata in snowflake.’
>
> **Action:**
> I started work on the api, since there is a previous java version api to call the api, I talked with the prduct owner, figure out hte security issue and all the details api. I also provode the fake data to developer A make sure I’m not stopping his works. in daily stand up meitn,g we show the process.
>
> **Result:**
> After two weeks, me an team deliver the data into snowflake, the process can sync the 33 tables data into snowflake, and each tablke contains 100 columns. I aslo learn and impove my cabalility to collabrate with tema mate in short time and how t o continue my work and don’t block other’s works.



### 3.12 Hire and Develop the Best – 雇用和培养最好的员工

- **Leaders raise the performance bar with every hire and promotion. They recognize exceptional talent, and willingly move them throughout the organization. Leaders develop leaders and take seriously their role in coaching others. We work on behalf of our people to invent mechanisms for development like Career Choice.**

  领导者在每次招聘和晋升时都会提高业绩标准。他们会发现杰出的人才，并愿意在整个公司内给予他们新的职责并加以历练。领导者培养领导者，他们认真指导下属，并以身作则。他们切身为员工着想，并努力帮助员工打造最好的职业发展平台

- 考点

  - 人们应该愿意为同龄人或加入公司的新亚马逊人开发技能做出贡献。领导者应该在整个组织中调动人才，同时牢记业务的成功以及员工的成长和发展。

- 准备

  - 想一下自己帮助过的人

#### How you help your team members develop their careers 

- This relates with “Hire and Develop the Best”

> **Situation:** 
>
> when I lead. the  web application, I lead two front end developer
>
> **Task:**
>
> I found one devoper A she is pretty intersed with the backend api.  she like view view the api code,
>
> **Action:**
> I encourage her to keep learing on the api part, and be a full stack web app devloper, I aslo show her some userful ressouce she can use. to learn in the firm, and some useful training resurl in the interal firm. I work with her the set the gola for the career. and teach her when she has quseitons.
>
> **Result:**
> I wokred with her half year, after half yea,r shie is formilar with the api a,dn spring boot framework and also the jdbc connections, and Kafka.  she could even starthe codes by picign some small tickets. I also learn how to encourae people in correct way.

#### A time when you provided feedback to develop & leverage the strengths of someone on your team. 

- This relates with “Hire and Develop the Best”

> **Situation:**
>
> **Task:**
>
> **Action:**
>
> **Result:**



### 3.13 Frugality – 节俭

- **Accomplish more with less. Constraints breed resourcefulness, self-sufficiency and invention. There are no extra points for growing headcount, budget size or fixed expense.**

  花小钱办大事。限制条件产生智谋、自立和创意。增加员工人数，预算规模和公司固定开支不会带来额外的好处。

- **考点**

  - 节俭就是为客户提供更多价值，同时降低成本。这一切都是为了提供最好的限制和约束。节俭不仅是为了成本，也是为了时间和资源，这就是亚麻原则中的以最佳方式管理事情。

- **准备**

  - 想一下自己什么时候帮助节约了运行时间
  - 想一下在有限资源下如何达成目标

### 3.14 Have Backbone; Disagree and Commit – 敢于谏言 服从大局

- Leaders are obligated to respectfully challenge decisions when they disagree, even when doing so is uncomfortable or exhausting. Leaders have conviction and are tenacious. They do not compromise for the sake of social cohesion. Once a decision is determined, they commit wholly.

  领导者必须要能够不卑不亢地质疑他们无法苟同的决策，哪怕这样做让人心烦意乱，精疲力尽。领导者要信念坚定，矢志不移。他们不会为了保持一团和气而屈就妥协。一旦做出决定，他们就会全身心地致力于实现目标。

- **考点**

  - 当两方存在争议时，这很平常，有人最后坚决反对，十分坚定地说“不”，也说清楚了个中缘由，在这样的情况下，你依然能同意去尝试不同的选择，并且给予全力支持。
  - 也就是说，即使你不认同，你也会给出自己的承诺去尝试。

- **准备**

  - 当你与同事发生争执或者产生分歧时，你是如何处理的
  - 你是否收到过别人的批评，你是如何回复的

#### A time you decided to go along with the group decision even if you disagreed

- This relates with “Have Backbone; Disagree and Commit”

> **Situation:** I got assgin task to use analyize the log of the sql process and make use whic one is running to long,
>
> **Task:**
> I want to kill the process, but the group want just show the process the notice the user,
>
> **Action:**
> I follow with them
>
> **Result:**
>
> show my idea. be honest to every, and collabrate with the group.

#### A time when you strongly disagreed with your manager on something you deemed very important the business

- This relates with “Have Backbone; Disagree and Commit”

> **Situation:**
> when I lead web application, the applicaiton is used to create the task metadata, 
>
> **Task:**
> manager ask me to create. the sql procedure to implement the major process. 
>
> **Action:**
>
> it will slow down the process, and should focus on the process.
>
> **Result:**
> My manger aggree on me, i lean how to sho wthe comimit and how to instis on my own ieda.

### **Tell me about a time you disagreed with your manager**

- This relates to "Dive Deep" (challenging assumptions and respectfully disagreeing to achieve better outcomes) and "Have Backbone; Disagree and Commit" (voicing dissent while still supporting the team's decisions).

  > **Situation:**
  > When I was working in Vanguard, my pervious company. We were tying to build a data etl pipeline to migrate data from oracl into AWS S3. It’s simily project that we did to migrate MSSQL data into AWS S3,  my manager outlined a development plan based on the information available. and He thought we can finish it within one month. It’s similar project, but we connect with different database, and we were running in AWS EMR.  my experience and insights suggested that we may need two more weeks to do additional POC to figure out the security of connection to Oracle in AWS. 
  >
  > **Task:**
  > As a team lead, I must ensure the project's success and devliever it as we promissed to our client. case our use will demo on the high level.
  >
  > **Action:**
  > Recognizing the importance of challenging and uncertain POC, I scheduled a meeting with my manager to discuss my concerns. I shared the additional details and insights I had gathered, explaining how they could impact the project's success.
  >
  > In the meeting, I proposed adjustments to the development plan based on my experience with similar projects. I emphasized the difference between this new porject and our past projects. I suggest we may need two more weeks for poc and my manager agree on it. After it, I also schedule meeting with our client and manager, to tell them the details and why we need 2 more weeks to deliver the porject. they all agree on it. 
  >
  > After meeting, I imidettly start the POC, schedule meeting with Oracle team to figure out the security connection with oracle database across the EMR. 
  >
  > **Result:**
  > Finally, I finished the POC within one week , and I deleivered the project into producion within one week earilier. My manager appreciated my perspective and agreed to consider my suggestions. By speaking up respectfully, I contributed to a stronger development plan that aligned with our project goals. It’s showing the importance of open communication and constructive feedback in achieving success as a team. it’s still a good leran that  I should ask or propose when I have different opinion and be open to talk with the team. 

#### A time when you submitted a good idea to your manager and he/she did not act

- This relates with “Have Backbone; Disagree and Commit”

> **Situation:**
> FTP server pull 3 rd party data from the local into hdfs. need to check the log to see if something wrong. Hard to track process.
>
> **Task:**
> Then I ghout the process can run on the ariflow wtih file sensor. it’s easy to track and mantainance.  I propose my idea to manager, show the pro and con. but he agree on it, but did make it in the plan.
>
> **Action:**
> I check the code and took a poc to show that it’s doable, demoe it to him.
>
> **Result:**
> I migrat the process into afirlow, easy to track the system, and show the process, and sasy to mataince the code and make high avaialbility. I also learn how to make it happened how to approve it to the manager.

#### A time you took an unpopular stance in a meeting with peers/leaders

- This relates with “Have Backbone; Disagree and Commit”

> **Situation:**
>
> **Task:**
>
> **Action:**
>
> **Result:**

### 3.15 Strive to be Earth’s Best Employer – 努力成为地球上的最佳雇主

- Leaders work every day to create a safer, more productive, higher performing, more diverse, and more just work environment. They lead with empathy, have fun at work, and make it easy for others to have fun. Leaders ask themselves: Are my fellow employees growing? Are they empowered? Are they ready for what’s next? Leaders have a vision for and commitment to their employees’ personal success, whether that be at Amazon or elsewhere.

  领导者每天都在努力创造一个更安全、更有生产力、更高绩效、更多样化和更公正的工作环境。他们用同理心来领导，在工作中获得乐趣，并让别人也享受工作的乐趣。领导者会问自己：我的员工在成长吗？他们获得足够的支持和鼓励吗？他们为下一步的工作做好准备了吗？领导者对员工的个人成功有愿景和承诺，无论是在亚马逊还是其他地方。

### 3.16 Success and ScaleBring Broad Responsibility – 成功和规模带来更大责任

- We started in a garage, but we’re not there anymore. We are big, we impact the world, and we are far from perfect. We must be humble and thoughtful about even the secondary effects of our actions. Our local communities, planet, and future generations need us to be better every day. We must begin each day with a determination to make better, do better, and be better for our customers, our employees, our partners, and the world at large. And we must end every day knowing we can do even more tomorrow. Leaders create more than they consume and always leave things better than how they found them.

  我们从一个车库起家，但我们已经不在那里了。我们是大型公司，我们影响着世界，而且我们远非完美。我们必须谦虚，对我们所有行为的影响也要深思熟虑。我们的社区、地球和后代需要我们每天都做得更好。我们必须以一种决心开始每一天，那就是为我们的客户、我们的员工、我们的合作伙伴，以至于整个世界做得更多，做得更好。亚马逊明白，随着他们的贡献和影响力的增长，员工也必须注意他们的决定对更多受众的影响。无论领导者做什么，都应该负起责任。





