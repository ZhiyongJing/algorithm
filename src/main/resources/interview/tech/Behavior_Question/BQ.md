## 1. Introduce

> Hello, I’m Jimmy. I’m a Senior Software Developer with a strong background in designing and developing data streaming, batched data processing systems, and distributed RESTful APIs.  I'm very professinal  in big data, distributed computing, concurrency, and cloud such as AWS technologies.
>
> In my current role at BlackRock, I have developed robust and distributed backend RESTful APIs for Sales CRM data-driven solutions, ensuring high performance, scalability, and reliability. I aslo led team of 3 front end developers to buid up a full web applicationI. Additionally, I have led  data streaming process project  to inegrate the data from local to cloud projects by using Kafka, MessageQueue.Built up data pipelines on Airflow for data migration into Snowflake, and collaborated closely with cross-functional teams to deliver high-quality software solutions.
>
> Previously I was working about 3 years at Vanguard Group, I leaded, designed and implemented large-scale distributed ETL pipelines in cloud for data migration and collaborated with the machine learning team to develop insightful business reports for guiding investment strategies.
>
> I’m graduated from Strony Brook University, majored in Compute Science.  and I own an AWS Certified Cloud Practitioner. My technical skills include expertise in Spring, Java, Python, Big data techs, and various database management systems, such as MSSQL  and Cassandra.
>
> I'm excited about the opportunity to discuss how my skills and experiences align with the goals of your team. Thank you!

## 2. Questions

### Why Amazon

Amazon is is a great company, leading the world in online shopping. Personally, I'm a big fan of Amazon's online shopping. I almot go shopping on line in each week. As a software engineer, every time I’m shopping on the Amazon, I’m aloways thinking how the appliction is running bechine the screen, how the data are processing in the flow. When I’m reading the job descriptoin,  I think I’m the right person matched with job description and aligns with team’s need. I have confidence to do my contribution.  And also, from personal technical perspective,  it will be a greate opportunity for me to learn form the team,  leaarn form the firm and grow up with the firm.

### **Tell me about a project that you are most proud of and the most challenging project you worked on**

- This aligns with "Deliver Results" (achieving successful outcomes on projects) and "Invent and Simplify" (finding innovative solutions to challenges).

  > **Situation:**
  >
  > The most challenging project(The project I’m most proud of)  I worked on is in last year when I’m working in current company, the firm is migrating our data from on prem database into Azure Cloud. As data squad to support sales, we need to migrate our portfolio data into Azure dataverse and make it live update. 
  >
  > **Task:**
  > Since I have experince to with Kafka, Message queue, Java and Spring boot. I’m also very formilar with the data. So, I took the leadership to design and implement this real time data procesing application. We need to deliever the data in time, otherwise, it will directly impact our firm’s sale and income. At firm level, they did some poc and conirmed it’s working. But for me, at project level, it is the  first time to migrate our data into cloud. To build out the project,  I need to figure out important blocks.
  >
  > 1. the security to call cloud api, 
  > 2. how to utilize the 3rd party cloud api to pull from cloud and load the data into cloud, 
  > 3. how to map the data type accross the MSSQL to cloud storage.
  > 4. How to consume our mesage queue to get real time updated data. 
  > 5. The load strategy, batch size, retry times when api is not working. 
  > 6. The availabity, scalability
  >
  > 
  >
  > **Action:**
  > To tackle this challenge, I lead two junior application engineer to conduct a comprehensive analysis of the requirements, considering factors such as data volume we are going to store, how many updated record message we are going to get from Message queue.   I listed all the important facors and buit a draft.
  >
  > Then, I collaborated closely with cross-functional teams, including Microsoft cloud support team, our salse user , and product owners, to gather requirements and refine the application architecture. After that, I worked with pdm to create a development pland and believe we can archive it in two months.
  >
  > When I assign the work the my team mates, based on their expertise, I assign the infrature structure setup to one who is more famaily with it. and Assign the data interaction work to one who is good at the data part. I maily focus on loading data into the cloud by using the API.
  >
  > Since it’s sales data, I need make sure the data  make data high availability, I decided to use two server running in same time and use Zookeeper as distrubiton lock to manage them.
  >
  > To figure out how to use  3rd party cloud API to work with our data,  I leveraged various resources such as offical documents, online courses, and developer communities to gain insights and practical knowledge about the API. Additionally, I buid up a poc project,  scheduled daily meeting with the cloud support them to ask the questions that I encountered in my learnning. And also schedule daily meeting with our product owner to show the the process and milesone we archived.
  >
  > Throughout the project, I encountered several challenges, including optimizing data processing algorithms to handle high data volumes and em beded with 3rd part API.  However, I approached these challenges with determination and creativity, leveraging my problem-solving skills and technical expertise to find innovative solutions.
  >
  > **Result:**
  > Finally, within two months, The real-time data streaming application was successfully deployed, exceeding expectations in terms of performance, scalability, and reliability. The project are able to handle a full load with  millions records  and hudread reocrd of live update per mins. It significantly become a standard for our coming data loading project.
  >
  > This project stands out to me as both the most challenging and rewarding project I've worked on. It exemplifies my ability to deliver results in complex and dynamic environments while demonstrating a commitment to innovation and continuous improvement.
  >
  > 
  >
  > Follow up:
  >
  > **Challenge**: Need to get this new server done in 2 months. I did poc, make sure it works, then scheduled meeting with pdm, manager and prduct owener, create a development to make sure we can make it. Schedule meeting with them per 2 days and give them update.
  >
  > **Conflict**: The conflict is we are tryong to build a robust project in one time, but users are still unclear for some additional data, since it’s need to be done in 2 months, so I talked with the product owner and pdm and manager. I made development plan of  phase 1 to for basic needs and phase 2 for additional things if they want.  Finally, I got it done.
  >
  > **ambiguity(模糊不清)**: When I was doing the poc, the batch size when we load data into cloud is kind of unclear, cause it’s really data based. So when I was doing the poc, I tried similuate the real data in production, did the test by passing with different argumnents to call the api, make sure we are using the highest capacity without cause any api errors. I did schedule meeting with Miscrosft support team to ask key features of the API, and what is the limitation.
  >
  > **Learn**: From technical perspective, I build knowledge to utilze the 3rd part api to load data into our data lake, I can provide my experience to the team mate and do the consulting to other team. Form team lead perspective, I proved my leadership skills, I gain more experience to lead the team to deliver the result in a short time.

### **Tell me about a time you disagreed with your manager**

- This relates to "Dive Deep" (challenging assumptions and respectfully disagreeing to achieve better outcomes) and "Have Backbone; Disagree and Commit" (voicing dissent while still supporting the team's decisions).

  > **Situation:**
  > When I was working in Vanguard, my pervious company. We were tying to build a data etl pipeline to migrate data from oracl into AWS S3. It’s simily project that we did to migrate MSSQL data into AWS S3,  my manager outlined a development plan based on the information available. and He thought we can finish it within one month. It’s similar project, but we connect with different database, and we were running in AWS EMR.  my experience and insights suggested that we may need two more weeks to do additional POC to figure out the security of connection to Oracle in AWS. 
  >
  > **Task:**
  > As a team lead, I must ensure the project's success and devliever it as we promissed to our client.
  >
  > **Action:**
  > Recognizing the importance of challenging and uncertain POC, I scheduled a meeting with my manager to discuss my concerns. I shared the additional details and insights I had gathered, explaining how they could impact the project's success.
  >
  > In the meeting, I proposed adjustments to the development plan based on my experience with similar projects. I emphasized the difference between this new porject and our past projects. I suggest we may need two more weeks for poc and my manager agree on it. After it, I also schedule meeting with our client and manager, to tell them the details and why we need 2 more weeks to deliver the porject. they all agree on it. 
  >
  >  After meeting, I imidettly start the POC, schedule meeting with Oracle team to figure out the security connection with oracle database across the EMR. I aslo did 
  >
  > **Result:**
  > Finally, I finished the POC within one week , and I deleivered the project into producion within one week earilier. My manager appreciated my perspective and agreed to consider my suggestions. By speaking up respectfully, I contributed to a stronger development plan that aligned with our project goals. It’s showing the importance of open communication and constructive feedback in achieving success as a team. it’s still a good leran that  I should ask or propose when I have different opinion and be open to talk with the team. 

### **Tell me about a time when you had a conflict with teammate**

- This relates to "Earn Trust" and "Have Backbone; Disagree and Commit." It's about handling conflicts respectfully and constructively.

  > **Situation:** 
  > During a project, I encountered a conflict with a colleague regarding the approach to be taken for a critical decision.
  >
  > **Task:** 
  > My task was to address the conflict in a respectful and constructive manner, while still maintaining trust and commitment to the project's goals.
  >
  > **Action:** 
  > First, I took a step back to understand the root cause of the conflict and the differing perspectives involved. I approached the situation with empathy, recognizing that everyone's input was valuable and stemmed from their desire to achieve the best outcome for the project.
  >
  > Next, I initiated a one-on-one conversation with my colleague to discuss our differing viewpoints openly and honestly. I actively listened to their concerns and viewpoints, seeking common ground and areas of compromise.
  >
  > During the discussion, I remained calm and focused on finding a resolution that aligned with the project's objectives. I proposed alternative solutions that addressed both our concerns and emphasized the importance of collaboration and teamwork in achieving our goals.
  >
  > **Result:** 
  > By approaching the conflict with a mindset of earning trust and respectfully disagreeing while remaining committed to the project's success, we were able to find a mutually acceptable solution. Our ability to handle the conflict constructively strengthened our working relationship and fostered a sense of trust and respect between us.
  >
  > The experience reinforced the importance of having backbone, respectfully navigating conflicts, and committing to the team's decisions even when we disagree. It ultimately contributed to a more positive and collaborative work environment, where conflicts were viewed as opportunities for growth and learning.

### **Tell me about a time you failed**

- This aligns with the "Learn and Be Curious" principle. Amazon encourages employees to take risks and learn from failures.

  > **Situation:**
  > During a data migration project to a dynamic platform, we encountered an unexpected issue after the release. The issue arose when we discovered that lookup columns were missing in the mapped tables, causing data discrepancies and errors.
  >
  > **Task:**
  > The task was to rectify the missing lookup columns swiftly to ensure data integrity and functionality were restored. This was critical to avoid disruptions to the system and maintain a positive user experience.
  >
  > **Action:**
  > Upon discovering the missing lookup columns post-release, I immediately took ownership of the issue. I initiated a thorough analysis to understand the root cause of the problem. Collaborating with the development team, we identified that the oversight occurred during the mapping process due to a misunderstanding of the data structure.
  >
  > To address the issue promptly, we decided on a quick fix approach. I led the effort to add the missing lookup columns to the mapped tables. This involved coordinating with database administrators to execute the schema changes efficiently while minimizing downtime.
  >
  > Throughout the resolution process, I ensured transparent communication with stakeholders, including project managers and team members, providing regular updates on the progress and expected timeline for resolution.
  >
  > **Result:**
  > The quick fix implementation allowed us to restore functionality and data integrity without significant disruption to the system. However, the incident served as a valuable learning experience for the team.
  >
  > We conducted a post-mortem analysis to identify the underlying causes of the issue and implemented preventive measures to avoid similar oversights in future migrations. This failure reinforced the importance of meticulous planning and thorough validation in data migration projects.
  >
  > Overall, while the oversight led to a temporary setback, it provided an opportunity for growth and learning. By embracing the principle of "Learn and Be Curious," we were able to turn the failure into a catalyst for continuous improvement and resilience in our development processes.

### **Tell me about a challenge you had and how you solved it** 

- This relates to several principles, including "Customer Obsession" (solving challenges to benefit the customer), "Ownership" (taking responsibility for solving problems), and "Deliver Results" (successfully resolving challenges).

  > **Situation:**
  > During a data migration from local to Azure dynamic project, we encountered a challenge when we needed to integrate a brand new third-party Dataverse API to load data into the cloud. Fully understand the how the api works become an important and urgent things to ensure the timely and successful completion of the migration.
  >
  > **Task:**
  > The task at hand was to learn and understand the intricacies of the third-party Dataverse API within a short timeframe. Mastery of the API was necessary to effectively utilize its capabilities for data migration and ensure a smooth transition for our users.
  >
  > **Action:**
  > Recognizing the importance of this challenge, I took ownership of the task and developed a comprehensive plan to expedite my learning process. I began by conducting in-depth research on the Dataverse API, studying its documentation, and exploring sample code and tutorials available online. 
  >
  > To accelerate my learning, I leveraged various resources such as online courses, forums, and developer communities to gain insights and practical knowledge about the API. Additionally, I scheduled daily meeting with the cloud support them to ask the questions that I encountered in my learnning .
  >
  > I  also created  sample projects and dry run to familiarize myself with its features and functionalities.
  >
  > Throughout the learning process, I maintained open communication with the project team, providing regular updates on my progress and any challenges encountered. I remained adaptable and receptive to feedback, adjusting my learning strategy as needed to overcome obstacles and achieve my objectives.
  >
  > **Result:**
  > Finally,  I successfully mastered the third-party Dataverse API within the required timeframe. I integrated the API into our data migration process, built up the data streaming project.
  >
  > By taking ownership of the challenge , I not only solved a critical problem, but also gained new tech skills. The successful integration of the Dataverse API ultimately Accelerated the other data migration. Additionally, my proactive approach to learning and problem-solving gave me confienece  to taking responsibility for solving challenges and driving results in my role as a software developer.

### **Tell me about a time when you did not meet expectations**

- This relates to "Deliver Results" and "Ownership." It's about taking responsibility for shortcomings and learning from them.

  > **Situation:**
  > During a new ETL pipeline project, we discovered the need for additional data to generate the final output, which was not initially anticipated.
  >
  > **Task:**
  > Our task was to design and implement an ETL pipeline to extract, transform, and load data into a target database. However, the requirement for additional data posed a challenge to our initial plan, which was not initially anticipated.
  >
  > **Action:**
  > Upon realizing the need for extra data, I took ownership of the situation. I communicated the issue transparently with the team and stakeholders and led efforts to identify and incorporate the necessary data sources into the pipeline.
  >
  > We adjusted the pipeline design and collaborated closely with data analysts to procure the required datasets. Throughout the process, I provided regular updates to stakeholders and ensured timely integration of the additional data.
  >
  > **Result:**
  > Despite our efforts, we couldn't deliver the feature as expected, which was disappointing for our team and the client. However, we turned this setback into a learning opportunity. As a senior developer, I took ownership of the situation and led the team in identifying areas for improvement. We implemented changes to our processes to enhance efficiency and better handle similar challenges in the future. This experience reinforced the importance of adaptability and continuous improvement in our work.



### **Tell me about a time you went above and beyond**

- This aligns with "Customer Obsession" and "Deliver Results." Going above and beyond to satisfy customers or achieve exceptional results exemplifies these principles.

  > **Situation:**
  > In one of project, our team was tasked with developing a software solution for a client within a tight deadline. During the project, I recognized that meeting the client's basic requirements wouldn't fully satisfy their needs.
  >
  > **Task:**
  > My goal was to deliver a solution that exceeded the client's expectations, demonstrating our commitment to customer satisfaction and exceptional results.
  >
  > **Action:**
  > after I relized it, I proactively engaged with the client to gain a deeper understanding of their long-term goals and challenges. I proposed innovative features and enhancements that would enhance the functionality and usability of the software. I collaborated closely with the development team to implement these enhancements, ensuring they were seamlessly integrated into the solution.
  >
  > Additionally, I took the initiative to provide regular progress updates to the client, soliciting feedback at every stage of development to ensure their expectations were continually met and exceeded. I went the extra mile to address any concerns or requests promptly, demonstrating our unwavering commitment to their satisfaction.
  >
  > **Result:**
  > By going above and beyond to deliver a solution, we not only met their immediate needs but also earned their trust and loyalty. The client was delighted with the final product, praising our team for our dedication, creativity, and customer-centric approach.
  >
  > Our proactive efforts not only strengthened our relationship with the client but also positioned us as a trusted partner capable of delivering exceptional results. This experience reinforced the importance of customer obsession and going above and beyond to deliver outstanding outcomes that truly make a difference.

### **Tell me about a time when you fought for something you believed in**

- This aligns with "Earn Trust" (standing up for what you believe is right, even in difficult situations).

  > **Situation:**
  > In a previous role, I encountered a situation where a proposed change in project direction seemed to compromise the quality and integrity of our work.
  >
  > **Task:**
  > My task was to advocate for maintaining our high standards and upholding the integrity of our project deliverables, even in the face of pressure to prioritize speed over quality.
  >
  > **Action:**
  > Recognizing the importance of standing up for what I believed in, I engaged in open and transparent discussions with project stakeholders. I articulated my concerns about the proposed change, emphasizing the potential risks and impact on the project's long-term success.
  >
  > I provided evidence and data to support my position, illustrating the potential consequences of compromising quality for the sake of expediency. Additionally, I collaborated with colleagues who shared similar concerns, forming a unified front to advocate for maintaining our standards.
  >
  > Despite facing resistance and pushback from some stakeholders, I remained steadfast in my conviction and continued to assert the importance of upholding our values and delivering a product of the highest quality.
  >
  > **Result:**
  > Ultimately, my efforts to fight for what I believed in paid off. Through persistent advocacy and evidence-based arguments, I earned the trust and respect of my colleagues and stakeholders. The proposed change was reconsidered, and measures were taken to ensure that quality remained a top priority throughout the project.
  >
  > By standing up for our principles and advocating for what was right, even in difficult situations, I demonstrated my commitment to earning trust and upholding the integrity of our work. This experience reinforced the importance of staying true to our values, even when faced with opposition, and ultimately contributed to the project's success.

### **Tell me about a time when you delivered a project late**

- This relates to "Deliver Results" and "Bias for Action" (taking action and delivering, even if it means adjusting deadlines).

  > **Situation:**
  > During a recent software development project, our team encountered unexpected hurdles, causing delays in the project timeline.
  >
  > **Task:**
  > Our goal was to deliver the project on time, ensuring it met all requirements and maintained high-quality standards.
  >
  > **Action:**
  > Despite meticulous planning, unforeseen technical issues cropped up during development, slowing our progress. As the deadline loomed, it became clear we wouldn't meet it.
  >
  > Acknowledging the urgency, we promptly informed stakeholders about the delay. We openly discussed the challenges and outlined our strategy for overcoming them.
  >
  > We worked tirelessly, reallocating resources and reorganizing tasks to expedite progress. Despite the obstacles, we maintained transparency and kept stakeholders informed.
  >
  > Although the project was delivered later than planned, we ensured the quality wasn't compromised. Our proactive approach and commitment to excellence were evident throughout.
  >
  > **Result:**
  > Despite the delay, stakeholders appreciated our transparency and dedication. By prioritizing quality and communication, we preserved trust and credibility. This experience emphasized the importance of adaptability and perseverance in delivering results, even when faced with unexpected setbacks.

### **Tell me about a time when you delivered something bigger than originally planned**

- This aligns with "Think Big" (pushing boundaries and delivering more than expected).

  > Certainly, here's the response using the STAR (Situation, Task, Action, Result) method:
  >
  > **Situation:** 
  > During a software development project, our team was tasked with creating a new application module to enhance a client's existing system.
  >
  > **Task:** 
  > Initially, we had scoped out a specific set of features to be included in the module. However, as we delved deeper into the project requirements and engaged with the client, we identified additional functionalities that could greatly benefit their business operations.
  >
  > **Action:** 
  > Recognizing the potential to deliver more value, I collaborated with the team to thoroughly analyze the feasibility of incorporating these extra features. We conducted detailed discussions with the client to understand their evolving needs and the impact of expanding the project scope.
  >
  > After gaining buy-in from both the client and our internal stakeholders, we formulated a plan to integrate the additional functionalities into the project. This involved reallocating resources, adjusting timelines, and refining our development approach to accommodate the expanded scope.
  >
  > Despite the challenges posed by the increased workload and tight deadlines, we remained committed to delivering exceptional results. Our team worked tirelessly, leveraging our expertise and creativity to implement the new features efficiently and effectively.
  >
  > **Result:** 
  > As a result of our efforts, we successfully delivered a module that exceeded the client's original expectations. The additional functionalities not only enhanced the overall functionality of the application but also provided the client with valuable tools to improve their business processes.
  >
  > Our proactive approach to thinking big and pushing the boundaries of the initial project scope earned accolades from the client, strengthening our relationship and positioning us as a trusted partner. This experience underscored the importance of innovation and flexibility in delivering solutions that truly make a difference for our clients.

### **Tell me about a time when you made something easier for the customer**

- This directly relates to "Customer Obsession" and "Simplify" (making processes easier and more efficient for customers).

  > **Situation:** 
  > During a software development project, our team identified a cumbersome process in the user interface that was causing frustration for our customers.
  >
  > **Task:** 
  > Our task was to streamline the process and make it more intuitive for users, aligning with our commitment to customer obsession and simplification.
  >
  > **Action:** 
  > To address this issue, we first conducted thorough usability testing and gathered feedback from customers to pinpoint pain points in the existing process. We identified several areas where the user interface could be improved to enhance usability and efficiency.
  >
  > Drawing upon these insights, we collaborated with our design and development teams to brainstorm solutions. We focused on simplifying the steps required to complete the task, removing unnecessary complexities, and enhancing the user experience.
  >
  > Once we had a clear plan in place, we proceeded to redesign the user interface, incorporating intuitive design elements and streamlining the workflow. We also introduced helpful tooltips and guided prompts to assist users at key points in the process.
  >
  > Throughout the implementation phase, we maintained open communication with our customers, seeking their input and feedback on the proposed changes. We ensured that the redesigned process met their needs and addressed their pain points effectively.
  >
  > **Result:** 
  > As a result of our efforts, we successfully transformed a cumbersome process into a seamless and intuitive experience for our customers. The redesigned user interface received overwhelmingly positive feedback from users, who appreciated the improvements in usability and efficiency.
  >
  > By prioritizing customer obsession and simplification, we not only enhanced the user experience but also strengthened customer satisfaction and loyalty. This experience reinforced the importance of continuously striving to make processes easier and more efficient for our customers, ultimately driving long-term success for our product.

### **Tell me about a time you disagreed with a company initiative**

- This relates to "Have Backbone; Disagree and Commit." It's about respectfully voicing concerns while still supporting the company's decisions.

  > **Situation:** 
  > In a previous role, our company proposed a new initiative to implement a specific technology framework across all projects.
  >
  > **Task:** 
  > While the initiative aimed to standardize development practices and improve efficiency, I had reservations about its suitability for certain projects.
  >
  > **Action:** 
  > To address my concerns, I engaged in discussions with colleagues and managers to understand the rationale behind the initiative and its potential impact on our projects.
  >
  > I researched alternative approaches and gathered data to support my viewpoint, highlighting potential challenges and limitations of the proposed framework in certain project contexts.
  >
  > In meetings and discussions, I respectfully voiced my concerns, presenting evidence and proposing alternative solutions that I believed would better align with the unique requirements of our projects.
  >
  > However, despite expressing my dissent, I remained open to dialogue and committed to supporting the company's decisions once they were made.
  >
  > **Result:** 
  > While my perspective ultimately did not sway the company's decision to proceed with the initiative, my contributions were valued and respected. By voicing my concerns respectfully and offering alternative solutions, I demonstrated my commitment to the company's success while upholding my principles.
  >
  > Although I disagreed with the initiative, I remained committed to its successful implementation and contributed actively to its execution. This experience reinforced the importance of having backbone, respectfully challenging decisions, and committing wholeheartedly to the company's goals.

### **Tell me about a time when you received negative feedback**

- This aligns with "Learn and Be Curious" and "Deliver Results." Negative feedback presents opportunities for growth and improvement.

  > **Situation:** 
  > During a performance review, I received negative feedback from a client regarding a project I had worked on.
  >
  > **Task:** 
  > My task was to address the feedback constructively and use it as an opportunity for growth and improvement.
  >
  > **Action:** 
  > Upon receiving the negative feedback, I took a moment to reflect on it and understand the specific areas where I had fallen short. I approached the feedback with a growth mindset, recognizing that there were areas where I could improve.
  >
  > I scheduled a follow-up meeting with the client to discuss their feedback in more detail. I actively listened to their concerns, seeking clarification on specific issues and asking for examples to better understand their perspective.
  >
  > Once I had a clear understanding of the feedback, I took proactive steps to address the areas of improvement. I sought guidance from mentors and colleagues, soliciting their advice on how to enhance my performance in those areas.
  >
  > I also took ownership of my mistakes and openly communicated with the client about the steps I was taking to address their concerns and prevent similar issues from arising in the future.
  >
  > **Result:** 
  > As a result of my proactive approach to addressing the negative feedback, I was able to turn the situation into a learning opportunity. By taking ownership of my mistakes and demonstrating a willingness to improve, I strengthened my relationship with the client and regained their trust.
  >
  > The experience taught me valuable lessons about the importance of actively seeking feedback, maintaining a growth mindset, and continuously striving for improvement. It reinforced the principles of "Learn and Be Curious" and "Deliver Results" by showing that negative feedback can be a catalyst for growth and ultimately lead to better outcomes.



### **Have you ever completed something not assigned to you?**

- This aligns with "Ownership" and "Bias for Action." Taking initiative to complete tasks beyond one's assigned responsibilities demonstrates ownership and a bias for action.

  > **Situation:** 
  > During a team meeting, I noticed that an important task critical for our project's success had been overlooked and was not assigned to anyone.
  >
  > **Task:** 
  > Recognizing the urgency of the task and its impact on our project timeline, I took it upon myself to ensure it was completed promptly.
  >
  > **Action:** 
  > Firstly, I approached the project lead to confirm the importance of the task and express my willingness to take ownership of it. Upon receiving approval, I immediately began working on it.
  >
  > I assessed the requirements and scope of the task, identified the necessary resources, and created a plan to execute it efficiently. Despite it not falling under my official responsibilities, I was determined to see it through to completion.
  >
  > Throughout the process, I maintained open communication with the team, providing updates on my progress and seeking input when needed. I also collaborated with colleagues to leverage their expertise and ensure the task was completed to the highest standard.
  >
  > **Result:** 
  > Thanks to my proactive approach and willingness to take ownership, the task was completed ahead of schedule, contributing to the overall success of the project. My initiative was recognized by the team and leadership, reinforcing the importance of ownership and bias for action in achieving our goals.
  >
  > This experience highlighted the value of being proactive and taking initiative, even when tasks are not explicitly assigned. It demonstrated my commitment to the team's success and willingness to go above and beyond to ensure project objectives are met.

### **Tell me about a time you delivered under a tight deadline considering all aspects**

- This aligns with "Deliver Results" and "Bias for Action." Successfully meeting tight deadlines demonstrates these principles in action.

  > Certainly, here's a response following the STAR method with a focus on the action:
  >
  > **Situation:** 
  > During a critical project, our team faced a sudden and unexpected deadline acceleration due to external factors.
  >
  > **Task:** 
  > Our task was to deliver the project on time while ensuring all aspects, including quality and stakeholder requirements, were considered.
  >
  > **Action:** 
  > Understanding the urgency of the situation, I immediately sprang into action. Firstly, I organized an emergency meeting with the team to assess the scope of work, identify critical tasks, and allocate resources effectively.
  >
  > I led the team in creating a detailed action plan, breaking down the project into manageable tasks with clear deadlines. We prioritized tasks based on their impact on the project's success and focused our efforts on completing them efficiently.
  >
  > Utilizing my project management skills, I implemented strategies to streamline workflows, eliminate non-essential tasks, and optimize collaboration among team members. I encouraged open communication and fostered a sense of urgency to keep everyone motivated and focused on the goal.
  >
  > We worked tirelessly, often putting in extra hours and leveraging each other's strengths to overcome challenges swiftly. I personally took on additional responsibilities, ensuring no aspect of the project was overlooked.
  >
  > **Result:** 
  > Thanks to our concerted efforts and proactive approach, we successfully delivered the project under the tight deadline. Despite the time constraints, we maintained a high standard of quality and met all stakeholder requirements.
  >
  > Our ability to deliver results under pressure earned praise from both the client and senior leadership, reinforcing the principles of "Deliver Results" and "Bias for Action." This experience underscored the importance of decisive action, effective teamwork, and relentless focus in meeting tight deadlines while considering all aspects of the project.

### **Tell me about a challenge you did not expect to encounter**

- This relates to "Invent and Simplify" (finding innovative solutions to unexpected challenges) and "Learn and Be Curious" (embracing challenges as opportunities to learn).

  > Certainly, here's a response following the STAR method with a focus on the action:
  >
  > **Situation:** 
  > During a complex software migration project, our team encountered a significant challenge that we hadn't anticipated.
  >
  > **Task:** 
  > Our task was to migrate a large dataset from an outdated system to a modern cloud-based platform while minimizing downtime and ensuring data integrity.
  >
  > **Action:** 
  > When we encountered the unexpected challenge, I immediately took charge of the situation. I gathered the team to assess the problem thoroughly and brainstorm potential solutions.
  >
  > We conducted extensive research and experimentation to understand the root cause of the challenge and identify innovative ways to address it. Despite the pressure of the tight deadline, I encouraged the team to remain curious and explore alternative approaches.
  >
  > Drawing upon our collective expertise, we devised a creative solution that leveraged advanced data migration techniques and automation tools. We developed a customized script to streamline the migration process and mitigate the impact of the challenge on the project timeline.
  >
  > I took the lead in implementing the solution, coordinating with various stakeholders and overseeing the execution of the migration plan. We closely monitored the process, making adjustments as needed to ensure its success.
  >
  > **Result:** 
  > Thanks to our proactive approach and innovative solution, we successfully overcome the unexpected challenge and completed the migration project on schedule. Our ability to think creatively and embrace challenges as opportunities to learn and innovate was instrumental in achieving a positive outcome.
  >
  > The experience reinforced the principles of "Invent and Simplify" and "Learn and Be Curious," highlighting the importance of adaptability and continuous learning in navigating unforeseen challenges.

### **Tell me about a time you could not deliver on a promise whether it's quality or deadline**

- This relates to "Deliver Results" and "Ownership." Taking responsibility for failures and learning from them is crucial.

  > **Situation:** 
  > During a software development project, our team encountered unforeseen technical challenges that impacted our ability to deliver on a promised deadline.
  >
  > **Task:** 
  > Our task was to develop and deploy a new feature within a specified timeframe to meet customer expectations.
  >
  > **Action:** 
  > Upon realizing that we were unable to meet the promised deadline due to technical complexities, I immediately took ownership of the situation. I convened an emergency meeting with the team to assess the issue and devise a plan of action.
  >
  > We conducted a thorough analysis of the technical challenges, identifying the root cause and potential solutions. Despite the setback, I remained transparent with stakeholders, communicating the situation honestly and outlining our plan to address it.
  >
  > We mobilized additional resources and adjusted our project plan to accommodate the unforeseen challenges. I led the team in prioritizing tasks and reallocating responsibilities to expedite the resolution process.
  >
  > Throughout the crisis, I maintained open communication with stakeholders, providing regular updates on our progress and managing expectations effectively. We worked tirelessly, often putting in extra hours and leveraging our collective expertise to overcome the obstacles.
  >
  > **Result:** 
  > Despite our best efforts, we were unable to deliver on the promised deadline. However, our proactive approach and transparent communication earned the trust and understanding of stakeholders. We ultimately delivered a high-quality solution, albeit slightly behind schedule.
  >
  > The experience reinforced the principles of "Deliver Results" and "Ownership," highlighting the importance of taking responsibility for failures and learning from them. It underscored the value of transparency, adaptability, and resilience in navigating challenges and ultimately delivering successful outcomes.

### **Tell me about a time you took on something that you were not comfortable with**

- This aligns with "Learn and Be Curious" and "Bias for Action." Taking on new challenges outside of one's comfort zone demonstrates a willingness to learn and take action.

  > **Situation:** 
  > During a critical project, I was asked to lead a team in implementing a new technology framework that I had limited experience with.
  >
  > **Task:** 
  > Although I was initially apprehensive about taking on this unfamiliar challenge, I recognized it as an opportunity for growth and development.
  >
  > **Action:** 
  > To address my discomfort and ensure success, I took proactive steps to familiarize myself with the new technology framework. I immersed myself in self-learning resources, tutorials, and documentation to gain a solid understanding of its concepts and best practices.
  >
  > I also sought guidance from colleagues and experts in the field, leveraging their expertise to fill knowledge gaps and gain insights into practical applications of the technology.
  >
  > Despite my initial discomfort, I embraced the challenge with enthusiasm and determination. I led the team with confidence, sharing my newfound knowledge and guiding them through the implementation process.
  >
  > Throughout the project, I remained open to feedback and continuously sought opportunities to learn and improve. I encouraged experimentation and innovation, fostering a culture of continuous learning and growth within the team.
  >
  > **Result:** 
  > Thanks to our collective efforts and my willingness to step out of my comfort zone, we successfully implemented the new technology framework, delivering a high-quality solution that exceeded expectations.
  >
  > Taking on something I was initially uncomfortable with proved to be a valuable learning experience, reinforcing the principles of "Learn and Be Curious" and "Bias for Action." It highlighted the importance of embracing challenges, pushing boundaries, and continuously expanding my skills and knowledge to achieve success.

### **Tell me about yourself and your background**

- This allows candidates to demonstrate various principles depending on how they frame their background and experiences, such as "Customer Obsession," "Invent and Simplify," and "Earn Trust."

### **Why Amazon?**

- This question allows candidates to demonstrate alignment with Amazon's principles, such as "Customer Obsession," "Invent and Simplify," and "Deliver Results.”

  > Certainly, here's a response following the STAR method with a focus on the action:
  >
  > **Situation:** 
  > Throughout my career, I've been drawn to organizations that prioritize innovation, customer-centricity, and continuous improvement.
  >
  > **Task:** 
  > When considering potential employers, I sought a company that not only shared my values but also provided a dynamic and challenging environment where I could thrive and make a meaningful impact.
  >
  > **Action:** 
  > Upon researching Amazon and learning about its leadership principles, I was immediately drawn to the company's relentless focus on customer satisfaction, innovation, and operational excellence.
  >
  > I was particularly impressed by Amazon's commitment to pushing boundaries, embracing new technologies, and continuously raising the bar to exceed customer expectations. The company's culture of accountability, ownership, and bias for action resonated with my own values and work ethic.
  >
  > To further understand Amazon's culture and values, I engaged with current and former employees, attended networking events, and immersed myself in Amazon's publications and resources.
  >
  > **Result:** 
  > Through my research and interactions, I gained a deep appreciation for Amazon's unique culture and the opportunities it offers for personal and professional growth. I was inspired by the company's commitment to excellence and its relentless pursuit of customer satisfaction.
  >
  > Joining Amazon aligns with my career aspirations and values, as it provides a platform for me to leverage my skills and expertise to drive meaningful impact and contribute to the company's success.
  >
  > By embracing Amazon's principles and culture, I am confident that I can make a positive difference and help deliver results that exceed expectations.

### **Why this position?**

- This allows candidates to articulate how their skills and experiences align with the specific responsibilities and objectives of the position, demonstrating principles like "Customer Obsession," "Ownership," and "Deliver Results.”

  > **Situation:** 
  > In my current role and throughout my career, I've consistently sought opportunities to apply my skills and expertise in a meaningful and impactful way.
  >
  > **Task:** 
  > When considering the senior application developer position at Amazon, I was excited by the prospect of contributing to a dynamic and innovative team focused on delivering exceptional solutions to customers.
  >
  > **Action:** 
  > I carefully reviewed the job description and identified key responsibilities and objectives that resonated with my experience and skill set. I recognized the opportunity to leverage my background in designing and developing robust software solutions to drive positive outcomes for Amazon's customers.
  >
  > To ensure alignment with the position's objectives, I conducted a thorough self-assessment, identifying areas where I could add value and make an immediate impact. I reflected on my past experiences and successes, drawing connections between my achievements and the requirements of the role.
  >
  > I also sought feedback from colleagues and mentors, gaining valuable insights into how my skills and expertise could complement the team and contribute to its success. Armed with this information, I tailored my application to highlight specific examples of how I had demonstrated principles like customer obsession, ownership, and delivering results in previous roles.
  >
  > **Result:** 
  > By taking proactive steps to align my skills and experiences with the requirements of the senior application developer position, I am confident that I can make a significant contribution to Amazon's objectives and deliver results that exceed expectations.
  >
  > I am excited about the opportunity to collaborate with talented individuals, leverage cutting-edge technologies, and drive innovation to create impactful solutions for Amazon's customers. Joining the senior application developer position aligns perfectly with my career goals and aspirations, and I am eager to bring my passion, expertise, and commitment to the role.

### **Why are you leaving your current company?**

- This can align with "Customer Obsession" (seeking opportunities to serve customers better) and "Bias for Action" (taking initiative to pursue new challenges).

  > **Situation:** 
  > In my current role, I've had the privilege of contributing to impactful projects and working alongside talented colleagues. However, as I reflect on my career goals and aspirations, I've come to the realization that I'm seeking new challenges and opportunities for growth.
  >
  > **Task:** 
  > When considering my decision to leave my current company, I carefully assessed my career trajectory and identified areas where I could further develop my skills and make a broader impact.
  >
  > **Action:** 
  > To ensure a thoughtful and proactive approach to my career progression, I engaged in self-reflection and conducted research on potential opportunities that aligned with my professional goals. I evaluated various factors, including the company's culture, values, and growth opportunities, to determine the best fit for my career aspirations.
  >
  > Recognizing Amazon's reputation as an industry leader with a strong commitment to innovation and customer-centricity, I was drawn to the opportunity to contribute to its dynamic and diverse team. I saw joining Amazon as a chance to leverage my skills and experiences to drive meaningful impact and further develop my career in a challenging and rewarding environment.
  >
  > **Result:** 
  > By taking action to explore new opportunities and pursue challenges aligned with my career goals, I am excited about the prospect of joining Amazon and contributing to its mission of customer obsession and innovation. I am confident that this decision will enable me to continue growing both personally and professionally, while also making a positive impact on the organization and its customers.

### **What are your strengths and weaknesses?**

- This relates to "Hire and Develop the Best" (knowing one's strengths and areas for improvement) and "Learn and Be Curious" (continuously seeking personal and professional growth).

  > Certainly, here's a response following the STAR method with a focus on the action:
  >
  > **Situation:**
  > Throughout my career, I've had the opportunity to reflect on my strengths and areas for improvement to better understand how I can contribute to my team and grow as a professional.
  >
  > **Task:**
  > When asked about my strengths and weaknesses during interviews, I took a proactive approach to self-assessment and self-improvement.
  >
  > **Action:**
  > To identify my strengths, I reflected on past experiences and successes, considering feedback from colleagues and supervisors. I recognized that my strengths lie in my ability to collaborate effectively with team members, my strong problem-solving skills, and my dedication to delivering high-quality work.
  >
  > In terms of weaknesses, I acknowledged that I tend to be overly critical of my own work at times, which can occasionally lead to self-doubt. However, I've taken steps to address this by seeking constructive feedback from others and learning to trust in my abilities.
  >
  > I also recognized that I have room for improvement in time management, particularly when juggling multiple tasks or projects simultaneously. To address this weakness, I've implemented strategies such as prioritizing tasks, setting clear deadlines, and breaking down complex projects into manageable steps.
  >
  > **Result:**
  > By taking proactive steps to understand my strengths and weaknesses, I've been able to leverage my strengths to contribute effectively to my team while actively working to improve in areas where I have identified weaknesses. This self-awareness and commitment to growth have enabled me to continuously learn and develop as a professional, ultimately leading to greater success in my career.

- > 

### **Tell me about a project that you had failed or a time you took a risk and succeeded/failed**

- This relates to "Deliver Results" (learning from failures and successes) and "Bias for Action" (taking calculated risks).

  > Certainly, here's a response following the STAR method with a focus on the action:
  >
  > **Situation:**
  > One project that comes to mind is when I led a migration effort to transition our data from an on-premises database to a cloud-based solution.
  >
  > **Task:**
  > The objective was to improve scalability and reliability by leveraging cloud technologies, such as AWS services, while minimizing downtime and ensuring data integrity.
  >
  > **Action:**
  > Initially, I meticulously planned the migration, considering factors such as data volume, schema compatibility, and application dependencies. However, during the execution phase, we encountered unforeseen challenges that resulted in unexpected downtime and data inconsistencies.
  >
  > Recognizing the urgency of the situation, I immediately assembled a cross-functional team to assess the issues and identify solutions. We conducted a post-mortem analysis to understand what went wrong and how we could prevent similar issues in the future.
  >
  > Despite the setbacks, we remained committed to delivering results and learning from our mistakes. We implemented corrective measures, such as refining our migration strategy, enhancing testing protocols, and improving communication among team members.
  >
  > **Result:**
  > While the project did not go as planned initially, the experience provided valuable insights and learnings. By embracing a culture of accountability and continuous improvement, we were able to overcome the challenges and ultimately succeed in migrating our data to the cloud successfully.
  >
  > This project taught me the importance of taking calculated risks, learning from failures, and continuously iterating to deliver results. It exemplifies my bias for action and my commitment to delivering high-quality outcomes, even in the face of adversity.

### **Tell me about a time you had to change the status quo**

- This aligns with "Think Big" (challenging existing norms and driving innovation) and "Dive Deep" (questioning assumptions and seeking improvements).

  > **Situation:**
  > In a previous role, I identified an opportunity to streamline our software development process by introducing Agile methodologies to replace our traditional waterfall approach.
  >
  > **Task:**
  > The objective was to improve collaboration, efficiency, and responsiveness to customer needs by adopting Agile principles and practices.
  >
  > **Action:**
  > To initiate the change, I first conducted thorough research on Agile methodologies and their potential benefits for our team and organization. I then presented my findings to leadership, highlighting the advantages of Agile in terms of adaptability, transparency, and customer satisfaction.
  >
  > Next, I organized training sessions and workshops to educate team members about Agile concepts and practices. I encouraged open dialogue and solicited feedback to ensure buy-in and alignment with our goals.
  >
  > Once the team was onboard, I facilitated the transition to Agile by restructuring workflows, implementing new tools and processes, and fostering a culture of collaboration and continuous improvement.
  >
  > Throughout the transition, I actively promoted the values of Agile, emphasizing the importance of iterative development, frequent communication, and customer-centricity. I led by example, demonstrating Agile principles in my own work and encouraging others to embrace change and innovation.
  >
  > **Result:**
  > The adoption of Agile methodologies led to tangible improvements in our development process, including increased productivity, faster time-to-market, and higher customer satisfaction. By challenging the status quo and driving innovation, we transformed our team into a more agile and responsive organization capable of delivering results more effectively and efficiently.

### **Tell me about a time you had to deal with a tight deadline and whether you were able to meet the target**

- This relates to "Deliver Results" (meeting deadlines and delivering under pressure) and "Bias for Action" (taking quick action to address challenges).

  > Certainly, here's a response following the STAR method with a focus on the action:
  >
  > **Situation:**
  > In a previous project, we were tasked with developing a critical software feature with a tight deadline to align with a product launch.
  >
  > **Task:**
  > The objective was to deliver a high-quality solution that met the project requirements within the specified timeframe.
  >
  > **Action:**
  > To address the tight deadline, I immediately mobilized the team and organized a sprint planning session to prioritize tasks and allocate resources effectively. We broke down the project into manageable tasks and established clear milestones to track progress.
  >
  > Recognizing the importance of communication and collaboration, I facilitated daily stand-up meetings to ensure alignment and transparency among team members. We regularly reviewed our progress and adjusted our approach as needed to stay on track.
  >
  > Despite the time constraints, I encouraged the team to maintain a focus on quality and craftsmanship. We implemented agile development practices such as test-driven development and continuous integration to minimize errors and ensure code quality.
  >
  > In addition, I proactively identified potential roadblocks and risks, taking preemptive action to mitigate them before they could impact our progress. This proactive approach helped us stay on course and maintain momentum throughout the project.
  >
  > **Result:**
  > Thanks to the team's collective efforts and our commitment to delivering results, we successfully met the tight deadline and delivered the software feature on schedule. The project's success was a testament to our ability to work effectively under pressure and deliver high-quality results in challenging circumstances. It highlighted the importance of collaboration, communication, and proactive problem-solving in achieving success, even when faced with tight deadlines.

### **Tell me about a time you had a conflict with your manager/peer/colleagues**

- This aligns with "Earn Trust" (handling conflicts respectfully) and "Have Backbone; Disagree and Commit" (voicing concerns while supporting team decisions).

  > **Situation:**
  > In a previous project, I encountered a conflict with a colleague regarding the prioritization of tasks and the allocation of resources.
  >
  > **Task:**
  > The objective was to address the conflict constructively while maintaining a positive working relationship and ensuring the success of the project.
  >
  > **Action:**
  > Initially, I approached the situation by seeking to understand the root cause of the conflict. I engaged in open and honest communication with my colleague to clarify our respective perspectives and identify areas of disagreement.
  >
  > During our discussions, I actively listened to my colleague's concerns and acknowledged their perspective, demonstrating empathy and respect for their opinions. I also shared my own viewpoint, articulating the reasons behind my proposed approach and the potential benefits for the project.
  >
  > Recognizing the importance of finding a mutually agreeable solution, I proposed a compromise that took into account both perspectives and balanced the competing priorities. I emphasized the importance of collaboration and teamwork in achieving our shared goals and encouraged my colleague to voice any additional concerns or suggestions.
  >
  > Throughout the conflict resolution process, I remained focused on maintaining a positive and constructive dialogue, avoiding personal attacks or confrontational behavior. I emphasized the value of open communication and transparency in building trust and fostering a supportive team environment.
  >
  > **Result:**
  > By approaching the conflict with empathy, respect, and a focus on collaboration, my colleague and I were able to resolve our differences and reach a consensus on how to move forward with the project. Our ability to handle the conflict respectfully and constructively strengthened our working relationship and contributed to the overall success of the project. This experience reinforced the importance of effective communication, empathy, and conflict resolution skills in building trust and fostering a positive team dynamic.

### **Tell me about a time you came up with a simple solution to a complex problem or a new approach to an old problem**

- This aligns with "Invent and Simplify" (simplifying processes and finding innovative solutions) and "Customer Obsession" (solving problems to benefit customers).

  > **Situation:**
  > In a previous project, we were facing challenges with optimizing the performance of our data processing pipeline, which was critical for ensuring timely and accurate data analysis for our customers.
  >
  > **Task:**
  > The objective was to find a simple yet effective solution to improve the efficiency and reliability of our data processing pipeline.
  >
  > **Action:**
  > To address this challenge, I conducted a thorough analysis of the existing data processing pipeline to identify bottlenecks and areas for improvement. I collaborated closely with the development team to gather insights into the underlying causes of performance issues.
  >
  > During our brainstorming sessions, I proposed a novel approach to redesigning the data processing pipeline using a more lightweight and streamlined architecture. Instead of overcomplicating the system with additional layers of complexity, I advocated for simplifying the pipeline by removing unnecessary components and optimizing resource utilization.
  >
  > With a focus on customer obsession, I ensured that the proposed solution would not only improve performance but also enhance the overall user experience by reducing processing times and increasing reliability.
  >
  > **Result:**
  > The implementation of the simplified data processing pipeline resulted in significant improvements in performance and reliability, exceeding our expectations. By taking a customer-centric approach and innovating to simplify the solution, we were able to achieve better outcomes for our customers while also streamlining our development process. This experience reinforced the importance of inventing and simplifying processes to drive innovation and deliver value to customers.

### **Tell me about feedback you received from your manager**

- This relates to "Learn and Be Curious" (seeking feedback for personal and professional growth) and "Deliver Results" (using feedback to improve performance).

  > **Situation:**
  > During a performance review meeting with my manager, I received feedback regarding my communication style and the need to improve clarity and transparency in my interactions with team members.
  >
  > **Task:**
  > The objective was to understand the specific areas for improvement identified by my manager and take proactive steps to address them.
  >
  > **Action:**
  > Upon receiving the feedback, I expressed appreciation for the candid assessment and demonstrated a commitment to leveraging the feedback for personal and professional growth.
  >
  > To gain a deeper understanding of the feedback, I requested specific examples and clarification from my manager to ensure alignment on the areas needing improvement. I also sought input from colleagues and peers to gather additional perspectives and insights.
  >
  > Armed with a clear understanding of the feedback, I developed an action plan outlining concrete steps to improve my communication skills. This included setting SMART (Specific, Measurable, Achievable, Relevant, Time-bound) goals for enhancing clarity, actively listening to others, and providing regular updates on project progress.
  >
  > I proactively sought opportunities to practice and refine my communication skills, such as volunteering to lead team meetings, participating in cross-functional collaborations, and soliciting feedback from colleagues on my communication effectiveness.
  >
  > **Result:**
  > Through consistent effort and dedication to improving my communication skills, I was able to demonstrate noticeable progress over time. My manager acknowledged the positive changes and expressed appreciation for my proactive approach to addressing the feedback.
  >
  > The feedback served as a catalyst for personal and professional growth, enabling me to become a more effective communicator and collaborator within the team. This experience reinforced the importance of seeking feedback as a means of continuous improvement and leveraging it to deliver better results.

### **Tell me about a time you made a decision without your manager's approval or with insufficient data**

- This aligns with "Bias for Action" (taking initiative even with incomplete information) and "Ownership" (taking responsibility for decisions).

  > **Situation:**
  > In a previous project, we encountered a critical issue with our production environment that required immediate attention to prevent service disruption.
  >
  > **Task:**
  > The objective was to make a timely decision and take action to address the issue, even though I didn't have my manager's approval and had limited data available.
  >
  > **Action:**
  > Recognizing the urgency of the situation, I quickly assessed the available information and consulted with team members to gather insights into the root cause of the issue. While we didn't have all the data we needed to make an informed decision, waiting for additional information was not an option due to the potential impact on service availability.
  >
  > With a bias for action, I took the initiative to propose a temporary solution based on the information available and my understanding of the problem. I outlined the potential risks and benefits of the proposed course of action and presented it to the team for discussion and feedback.
  >
  > Despite the uncertainty surrounding the decision, I took ownership of the situation and assumed responsibility for the consequences of my actions. I communicated transparently with my manager about the decision I had made and the rationale behind it, seeking their support and guidance in navigating the situation.
  >
  > **Result:**
  > The decision to take action without my manager's approval proved to be the right course of action, as it allowed us to mitigate the immediate risk and stabilize the production environment. While there were challenges along the way, including unforeseen complications and additional troubleshooting, the proactive approach ultimately prevented service disruption and minimized the impact on our customers.
  >
  > The experience reinforced the importance of taking decisive action in high-pressure situations, even with incomplete information, and assuming ownership of the outcomes. It demonstrated my ability to exercise sound judgment and make tough decisions in the best interest of the team and the organization.

### **Tell me about a time you were assigned a project with unclear responsibility**

- This relates to "Ownership" (taking accountability for clarifying roles and responsibilities) and "Dive Deep" (seeking clarity and understanding).

  > **Situation:**
  > In a previous project, I was assigned a complex task that lacked clear delineation of responsibilities among team members.
  >
  > **Task:**
  > The objective was to clarify roles and responsibilities to ensure efficient collaboration and successful project delivery.
  >
  > **Action:**
  > Upon receiving the assignment, I immediately recognized the importance of establishing clarity around roles and responsibilities to avoid confusion and prevent potential delays or misunderstandings.
  >
  > To address the ambiguity, I proactively initiated a discussion with the project manager and key stakeholders to gain a better understanding of the project objectives, scope, and deliverables. I asked clarifying questions to identify any areas of uncertainty and to ensure alignment on expectations.
  >
  > Next, I facilitated a team meeting to discuss the project requirements and expectations, emphasizing the importance of clear communication and collaboration. During the meeting, I encouraged open dialogue and solicited input from team members to identify their strengths, expertise, and areas of interest.
  >
  > Based on the insights gathered, I proposed a draft project plan outlining specific tasks, milestones, and responsibilities for each team member. I sought feedback and input from the team to refine the plan and ensure that everyone had a clear understanding of their roles and responsibilities.
  >
  > Throughout the project lifecycle, I continued to monitor progress and address any issues or challenges that arose. I maintained open lines of communication with team members, providing regular updates on project status and seeking feedback to ensure alignment and accountability.
  >
  > **Result:**
  > By taking proactive steps to clarify roles and responsibilities, we were able to establish a shared understanding of the project requirements and expectations among team members. This helped to streamline collaboration, improve communication, and enhance overall project efficiency.
  >
  > The clarity around responsibilities enabled team members to focus on their assigned tasks with confidence, leading to successful project delivery within the agreed-upon timeframe. The experience underscored the importance of taking ownership and initiative in clarifying responsibilities to ensure project success.

### **Tell me about a time you had to sacrifice short-term gains for long-term goals**

- This aligns with "Think Big" and "Long-Term Thinking" (making decisions that prioritize long-term success over short-term gains).

  > **Situation:**
  > In a previous project, I encountered a scenario where there was a temptation to pursue short-term gains at the expense of long-term goals.
  >
  > **Task:**
  > The objective was to make a decision that prioritized the long-term success and sustainability of the project, even if it meant sacrificing immediate benefits.
  >
  > **Action:**
  > When faced with the decision, I recognized the importance of considering the broader implications and consequences beyond immediate gains. I conducted a thorough analysis of the potential short-term and long-term impacts of different courses of action.
  >
  > While there were options that offered immediate benefits or shortcuts to achieving certain objectives, I remained committed to the project's long-term goals and vision. I engaged in discussions with key stakeholders to align on the importance of prioritizing long-term success and sustainability.
  >
  > To reinforce the importance of long-term thinking, I communicated the potential risks and trade-offs associated with pursuing short-term gains. I emphasized the need to invest in foundational elements and strategic initiatives that would position the project for sustained growth and success in the future.
  >
  > Despite the pressure to deliver quick wins or immediate results, I remained steadfast in my commitment to making decisions that aligned with the project's long-term objectives. I advocated for investing resources and effort into initiatives that would yield sustainable benefits over time, even if they required more time and patience to realize.
  >
  > **Result:**
  > By prioritizing long-term goals over short-term gains, we were able to lay a solid foundation for the project's future success and sustainability. While the decision may have involved some initial sacrifices or delays, it ultimately positioned the project for long-term growth and prosperity.
  >
  > The experience reinforced the importance of thinking big and prioritizing long-term success in decision-making processes. It underscored the value of making strategic investments and sacrifices in the present to achieve greater outcomes and impact in the future.

### **Tell me about a time you were halfway through a project and had to change direction due to a mistake**

- This relates to "Deliver Results" (adapting to unforeseen challenges) and "Dive Deep" (analyzing mistakes to find solutions).

  > **Situation:**
  > During a previous project, we encountered a significant setback halfway through the implementation phase due to a mistake that was made in the initial planning stages.
  >
  > **Task:**
  > The objective was to acknowledge the mistake, adapt to the new circumstances, and pivot the project in a new direction to achieve successful outcomes.
  >
  > **Action:**
  > Upon discovering the mistake, I immediately convened a meeting with the project team to assess the situation and determine the best course of action. I encouraged open and honest communication, emphasizing the importance of acknowledging the mistake and taking responsibility for addressing it.
  >
  > During the meeting, we conducted a comprehensive analysis of the root cause of the mistake and its impact on the project timeline and objectives. We identified the need to change direction and pivot our approach to mitigate the consequences of the mistake and ensure project success.
  >
  > Drawing on the principles of "Deliver Results" and "Dive Deep," I led the team in brainstorming alternative solutions and developing a revised project plan. We evaluated different strategies and considered their potential risks and benefits, taking into account lessons learned from the mistake.
  >
  > With a focus on action, we swiftly implemented the changes outlined in the revised project plan, reallocating resources, adjusting timelines, and communicating transparently with stakeholders about the new direction. We remained agile and adaptable, continuously monitoring progress and making course corrections as needed.
  >
  > **Result:**
  > By acknowledging the mistake and taking proactive steps to change direction, we were able to overcome the setback and ultimately achieve successful outcomes for the project. The experience reinforced the importance of resilience, adaptability, and accountability in navigating unforeseen challenges and delivering results.
  >
  > The ability to pivot in response to mistakes and learn from setbacks is crucial for driving continuous improvement and growth. This experience underscored the value of embracing mistakes as opportunities for learning and innovation, ultimately strengthening our team's capacity to deliver results in dynamic and evolving environments.

### **Tell me about a time you came up with a solution that the customer didn't ask for but ended up liking**

- This aligns with "Customer Obsession" and "Invent and Simplify" (anticipating customer needs and providing innovative solutions).

  > **Situation:**
  > During a software development project, we were tasked with enhancing a customer-facing application to improve user experience and functionality.
  >
  > **Task:**
  > The objective was to identify opportunities for innovation and provide solutions that would exceed the customer's expectations, even if they hadn't explicitly requested them.
  >
  > **Action:**
  > As we delved into the project requirements and engaged with stakeholders, I recognized an opportunity to add a feature that wasn't initially requested but had the potential to significantly enhance the user experience.
  >
  > Drawing on insights gathered from user feedback and market research, I proposed the idea of incorporating a real-time chat support feature directly within the application. While the customer hadn't specifically asked for this feature, I believed it would streamline communication between users and support representatives, ultimately improving customer satisfaction and retention.
  >
  > To gain buy-in from stakeholders, I presented a comprehensive analysis outlining the benefits of the proposed feature, including its potential to reduce customer support response times, increase user engagement, and differentiate the application from competitors.
  >
  > Despite initial skepticism from some stakeholders, I remained persistent in advocating for the inclusion of the chat support feature, emphasizing its alignment with the principles of "Customer Obsession" and "Invent and Simplify."
  >
  > **Result:**
  > Upon implementing the chat support feature and rolling it out to users, we received overwhelmingly positive feedback from customers who appreciated the added convenience and accessibility. The feature not only addressed users' needs in real-time but also contributed to a significant improvement in customer satisfaction metrics.
  >
  > The success of the chat support feature demonstrated the value of proactively identifying and addressing customer needs, even when they aren't explicitly articulated. By anticipating and exceeding customer expectations, we strengthened our relationship with the customer and solidified our reputation as a trusted partner committed to delivering innovative solutions.

### **Tell me about a time a customer requested something you knew they didn't actually want**

- This relates to "Customer Obsession" (understanding and fulfilling genuine customer needs) and "Dive Deep" (questioning assumptions to ensure customer satisfaction).

  > **Situation:**
  > During a customer interaction, they requested a specific feature that, based on our understanding of their needs and objectives, didn't align with their best interests.
  >
  > **Task:**
  > The objective was to engage with the customer to uncover the underlying reasons for their request and provide a solution that better addressed their genuine needs, despite their initial request.
  >
  > **Action:**
  > Upon receiving the customer's request, I engaged in a thorough discussion with them to gain a deeper understanding of their objectives and pain points. I listened attentively to their concerns and asked probing questions to uncover the underlying reasons behind their request.
  >
  > During the conversation, it became apparent that the requested feature wasn't the most effective solution to address their underlying needs. Drawing on my expertise and understanding of our product offerings, I proposed an alternative solution that better aligned with their objectives and would deliver greater value.
  >
  > I presented the alternative solution to the customer, outlining its benefits and how it addressed their core needs more effectively than the initially requested feature. I provided examples and case studies to illustrate the potential impact of the proposed solution on their business outcomes.
  >
  > Despite initial resistance from the customer, I remained patient and empathetic, continuing to engage in open dialogue and address any concerns they raised. I emphasized our commitment to their success and our desire to provide solutions that genuinely met their needs.
  >
  > **Result:**
  > Through collaborative discussion and mutual understanding, the customer ultimately recognized the value of the alternative solution proposed. They appreciated our willingness to dive deep into their requirements and provide tailored solutions that aligned with their long-term objectives.
  >
  > By challenging assumptions and diving deep into the customer's needs, we were able to foster trust and strengthen our relationship with the customer. The experience reinforced the importance of prioritizing customer success and delivering solutions that genuinely address their needs, even if it means challenging their initial requests.

### **Tell me about a time you had to convince the team or were convinced by the team**

- This aligns with "Earn Trust" (building credibility and consensus within the team) and "Have Backbone; Disagree and Commit" (voicing opinions while respecting differing viewpoints).

  > **Situation:**
  > During a project meeting, there was a disagreement within the team regarding the selection of a technology stack for an upcoming development initiative.
  >
  > **Task:**
  > The objective was to align the team on the most suitable technology stack that would meet project requirements and ensure successful outcomes.
  >
  > **Action:**
  > I recognized the importance of fostering open communication and collaboration within the team to reach a consensus on the technology stack. To address the disagreement, I facilitated a constructive discussion during the meeting, encouraging team members to voice their perspectives and concerns openly.
  >
  > As the discussion unfolded, it became evident that there were differing opinions on the optimal technology stack based on individual expertise and preferences. Rather than advocating for a specific technology stack solely based on my own viewpoint, I listened attentively to each team member's rationale and insights.
  >
  > Drawing on the principles of "Earn Trust" and "Have Backbone; Disagree and Commit," I engaged in active dialogue with team members to explore the strengths and limitations of each proposed technology stack. I asked probing questions to deepen our understanding of the technical requirements and potential implications of each option.
  >
  > Despite initially leaning towards a particular technology stack, I remained open-minded and receptive to alternative perspectives presented by team members. I acknowledged the validity of their arguments and demonstrated a willingness to reconsider my stance based on new information and insights.
  >
  > **Result:**
  > Through collaborative discussion and mutual respect, the team ultimately reached a consensus on the most suitable technology stack for the project. By fostering open dialogue and embracing diverse viewpoints, we leveraged collective expertise to make an informed decision that aligned with project objectives and technical requirements.
  >
  > The experience reinforced the importance of building trust and credibility within the team by valuing and respecting differing opinions. By encouraging open communication and embracing diverse perspectives, we strengthened team cohesion and enhanced our ability to make well-informed decisions collaboratively.

### **Tell me about a project where you had to overcome a significant obstacle**

- This relates to "Deliver Results" (persevering through challenges to achieve success) and "Bias for Action" (taking proactive steps to overcome obstacles).

  > **Situation:**
  > During a previous project, we encountered a significant obstacle when a critical third-party service that our application relied on experienced a sudden outage.
  >
  > **Task:**
  > The objective was to overcome the obstacle and ensure the successful completion of the project within the designated timeline.
  >
  > **Action:**
  > Upon discovering the outage, I immediately convened a cross-functional team to assess the impact on our project timeline and identify potential solutions. We acknowledged the severity of the situation and the urgency of finding a resolution.
  >
  > Drawing on the principles of "Deliver Results" and "Bias for Action," we wasted no time in developing a contingency plan to mitigate the impact of the outage. We explored alternative approaches and workarounds to minimize disruption to our project timeline and maintain progress towards our goals.
  >
  > I took a leadership role in coordinating efforts across different teams, ensuring clear communication and alignment on the steps needed to overcome the obstacle. We leveraged our collective expertise and resources to implement temporary solutions and workarounds while the third-party service was being restored.
  >
  > Despite the challenges posed by the outage, we remained focused on our objectives and maintained a proactive mindset. We continuously monitored the situation, making adjustments and refinements to our contingency plan as needed to address emerging issues and minimize the impact on project deliverables.
  >
  > **Result:**
  > Through collaborative teamwork, proactive problem-solving, and relentless determination, we successfully navigated through the obstacle posed by the third-party service outage. Despite the initial setback, we were able to adapt and overcome the challenge, ultimately delivering the project within the designated timeline and meeting our objectives.
  >
  > The experience reinforced the importance of resilience, agility, and teamwork in overcoming obstacles and delivering results in dynamic and unpredictable environments. By embracing a proactive approach and maintaining a relentless focus on our goals, we demonstrated our commitment to delivering success even in the face of adversity.

### **Tell me about an experience where you had to earn trust or gain buy-in from another group**

- This aligns with "Earn Trust" and "Collaborate Effectively" (building relationships and gaining support from stakeholders).

  > **Situation:**
  > During a previous project, we needed to gain buy-in from another department for a critical change that would impact their workflows.
  >
  > **Task:**
  > The objective was to earn the trust and cooperation of the other department to successfully implement the change and achieve our project goals.
  >
  > **Action:**
  > Recognizing the importance of building trust and collaboration with the other department, I took a proactive approach to engage with their key stakeholders. I scheduled meetings to discuss the proposed change, emphasizing the benefits it would bring to both teams and the organization as a whole.
  >
  > During these meetings, I listened attentively to their concerns and perspectives, demonstrating empathy and understanding of their challenges. I highlighted how the proposed change aligned with their objectives and how it would streamline processes and improve efficiency for both teams.
  >
  > To further build trust and credibility, I offered to provide demonstrations and proof-of-concept sessions to showcase the potential impact of the change. I collaborated with their team to address any questions or reservations they had, ensuring transparency and openness throughout the process.
  >
  > In addition, I actively sought feedback and input from their team members, incorporating their suggestions and concerns into our plans. By involving them in the decision-making process, I fostered a sense of ownership and partnership, which helped to build trust and buy-in for the proposed change.
  >
  > **Result:**
  > Through ongoing communication, collaboration, and relationship-building efforts, we were able to earn the trust and cooperation of the other department. They recognized the value of the proposed change and the benefits it would bring to their workflows and the organization as a whole.
  >
  > As a result, they fully supported the implementation of the change, actively participating in the planning and execution phases. The successful collaboration between our teams not only facilitated the smooth implementation of the change but also strengthened our working relationship and laid the foundation for future collaboration.
  >
  > The experience reinforced the importance of proactive communication, empathy, and collaboration in building trust and gaining buy-in from stakeholders. By fostering open dialogue and demonstrating a commitment to mutual success, we were able to overcome resistance and achieve our shared objectives.

### **Tell me about a time you stepped outside of your job scope and solved a problem**

- This relates to "Ownership" (taking initiative to solve problems regardless of job boundaries) and "Bias for Action" (taking proactive steps to address issues).

  > **Situation:**
  > During a critical project, our team encountered a technical issue that fell outside the scope of my usual responsibilities.
  >
  > **Task:**
  > The objective was to resolve the issue promptly to minimize its impact on the project timeline and ensure successful delivery.
  >
  > **Action:**
  > Recognizing the urgency of the situation and the potential impact on the project, I took ownership of the problem and proactively engaged in finding a solution. Despite the issue falling outside my job scope, I knew that addressing it was crucial for the success of the project.
  >
  > I began by conducting thorough research to understand the root cause of the technical issue and explore potential solutions. Drawing on my technical expertise and experience, I devised a plan to address the issue effectively, leveraging available resources and collaborating with relevant stakeholders.
  >
  > I reached out to colleagues and subject matter experts for their insights and assistance, demonstrating a willingness to seek help and collaborate across teams to resolve the issue. Together, we brainstormed ideas, tested hypotheses, and iterated on potential solutions until we identified the most effective approach.
  >
  > Taking initiative, I implemented the solution and closely monitored its impact to ensure its effectiveness. I remained proactive in communicating updates to the project team, keeping them informed of our progress and any adjustments made along the way.
  >
  > **Result:**
  > Through my proactive efforts and collaboration with colleagues, we successfully resolved the technical issue and mitigated its impact on the project timeline. By stepping outside my job scope and taking ownership of the problem, I demonstrated a commitment to delivering results and ensuring the success of the project.
  >
  > The experience reinforced the importance of taking initiative and being willing to go above and beyond job boundaries to address challenges and drive positive outcomes. By embracing a mindset of ownership and bias for action, I was able to contribute to the project's success and strengthen my reputation as a reliable and proactive team member.

### **What is your proudest/biggest innovation and how do you measure project success?**

- This aligns with "Invent and Simplify" (innovating to create value) and "Deliver Results" (measuring success based on impact and outcomes).

  > **Situation:**
  > In a previous role, I led a project to develop a real-time data streaming application that significantly enhanced our organization's ability to process and analyze large volumes of data.
  >
  > **Task:**
  > The objective was to innovate and streamline our data processing capabilities to improve decision-making and drive business outcomes.
  >
  > **Action:**
  > Recognizing the need for a more efficient and scalable solution, I took the initiative to propose and develop a real-time data streaming application leveraging cutting-edge technologies such as Apache Kafka and Apache Spark.
  >
  > I collaborated closely with cross-functional teams to gather requirements, design the architecture, and implement the solution. Drawing on my technical expertise and innovative mindset, I designed the application to handle high volumes of data in real-time, enabling faster insights and more informed decision-making.
  >
  > Throughout the project, I continuously monitored key metrics such as data throughput, latency, and system stability to ensure the application met performance requirements. I iterated on the design and implementation based on feedback from stakeholders, refining the solution to optimize performance and usability.
  >
  > **Result:**
  > The real-time data streaming application I developed proved to be a game-changer for our organization, providing stakeholders with timely insights and enabling faster, data-driven decision-making. By streamlining our data processing capabilities, we were able to significantly reduce processing times and improve the accuracy of our analyses.
  >
  > The success of the project was measured not only by the technical capabilities of the application but also by its impact on business outcomes. We observed tangible improvements in operational efficiency, cost savings, and customer satisfaction, demonstrating the value of the innovation.
  >
  > Overall, this project stands as my proudest innovation, showcasing my ability to drive meaningful change through technology and deliver tangible results that positively impact the organization. By focusing on innovation and measuring success based on impact and outcomes, I was able to drive meaningful change and contribute to the success of the organization.

### **Have you ever learned something new independently and used it to solve work problems?**

- This relates to "Learn and Be Curious" (continuously seeking knowledge and applying it to improve performance) and "Invent and Simplify" (finding innovative solutions through learning).

  > **Situation:**
  > In a previous role, I encountered a technical challenge that required expertise in a new technology that I had not previously used in my work.
  >
  > **Task:**
  > The objective was to find a solution to the challenge by independently learning and applying the new technology.
  >
  > **Action:**
  > Recognizing the need to acquire new skills to address the challenge effectively, I took the initiative to embark on a self-directed learning journey. I dedicated time outside of work hours to research and study the new technology, leveraging online resources, tutorials, and documentation.
  >
  > I immersed myself in learning the fundamentals of the technology, experimenting with code samples and building small projects to deepen my understanding. As I gained proficiency, I began to explore how the new technology could be applied to solve the specific work problem I was facing.
  >
  > I collaborated with colleagues and sought feedback to validate my approach and ensure alignment with project objectives. Drawing on my newfound knowledge, I developed a solution that leveraged the capabilities of the new technology to address the technical challenge effectively.
  >
  > **Result:**
  > By independently learning and applying the new technology, I was able to successfully overcome the technical challenge and deliver a solution that met project requirements. The solution not only resolved the immediate problem but also opened up new possibilities for leveraging the technology in future projects.
  >
  > The experience reinforced the importance of continuous learning and self-improvement in driving innovation and problem-solving. By embracing a mindset of curiosity and proactively seeking out new knowledge, I was able to expand my skill set, overcome obstacles, and deliver value to the organization.

### **How do you handle conflicting goals and make trade-offs?**

- This aligns with "Deliver Results" (prioritizing goals to achieve successful outcomes) and "Bias for Action" (taking decisive action to resolve conflicts).

  > **Situation:**
  > In a previous project, I encountered conflicting goals between different stakeholders, which required careful navigation and decision-making to ensure successful outcomes.
  >
  > **Task:**
  > The objective was to handle conflicting goals effectively and make informed trade-offs to maintain project progress and achieve desired results.
  >
  > **Action:**
  > First, I initiated open and transparent communication with all stakeholders involved to understand their perspectives, priorities, and concerns. By facilitating constructive dialogue, I gained valuable insights into the underlying reasons for the conflicting goals and identified potential areas of compromise.
  >
  > Next, I conducted a comprehensive analysis of the project requirements, constraints, and dependencies to assess the feasibility of various options and their potential impact on project outcomes. I engaged in collaborative problem-solving sessions with the stakeholders to explore alternative solutions and evaluate the trade-offs involved.
  >
  > Drawing on my analytical skills and decision-making abilities, I synthesized the information gathered and proposed a balanced approach that addressed the key objectives of each stakeholder while minimizing negative impacts on project timelines and resources. I prioritized goals based on their strategic importance and aligned them with the overall project vision.
  >
  > Throughout the process, I remained flexible and adaptable, ready to adjust course as new information emerged or circumstances changed. I maintained a focus on achieving the best possible outcomes for the project as a whole, even if it meant making difficult decisions or trade-offs along the way.
  >
  > **Result:**
  > By effectively handling conflicting goals and making informed trade-offs, I was able to maintain project momentum and drive successful outcomes. The collaborative approach fostered trust and alignment among stakeholders, enabling us to overcome challenges and achieve our collective goals.
  >
  > The experience reinforced the importance of clear communication, strategic thinking, and decisive action in navigating conflicting goals and delivering results. By embracing a mindset of collaboration and problem-solving, I was able to effectively manage complexity and drive project success.

### **Tell me about a time you had to seek outside help to find a solution**

- This relates to "Seek and Embrace Opportunities" (leveraging resources and expertise to find solutions) and "Learn and Be Curious" (being open to seeking help to overcome challenges).

  > **Situation:**
  > During a complex project, I encountered a technical challenge that required expertise beyond my current knowledge and experience.
  >
  > **Task:**
  > The objective was to find a solution to the challenge by seeking outside help and leveraging additional expertise.
  >
  > **Action:**
  > Recognizing the complexity of the challenge, I took proactive steps to seek outside help and access additional resources. I reached out to colleagues within my network who had relevant expertise or experience in the specific area related to the challenge.
  >
  > I also utilized online forums, community groups, and technical communities to pose questions and seek advice from experts in the field. By leveraging these external resources, I was able to gather valuable insights, perspectives, and potential solutions to address the technical challenge.
  >
  > I engaged in collaborative discussions with the external experts, sharing details of the challenge and seeking their input and recommendations. Through this collaborative exchange of ideas and knowledge sharing, I gained a deeper understanding of the problem and explored alternative approaches to finding a solution.
  >
  > **Result:**
  > By seeking outside help and leveraging additional expertise, I was able to identify a viable solution to the technical challenge and successfully implement it within the project timeline. The collaboration with external experts not only facilitated problem-solving but also enriched my own knowledge and skills in the process.
  >
  > The experience reinforced the importance of being open to seeking help and leveraging external resources to overcome challenges and drive innovation. By embracing a mindset of curiosity and collaboration, I was able to access valuable expertise and find creative solutions to complex problems.

### **Tell me about a time you volunteered to take on a project instead of being assigned to it**

- This aligns with "Ownership" (taking initiative to contribute beyond assigned tasks) and "Bias for Action" (proactively seeking opportunities to make a difference).

  > **Situation:**
  > In a previous role, I noticed a gap in our team's capacity to address a critical project that aligned with our strategic objectives.
  >
  > **Task:**
  > The objective was to volunteer to take on the project proactively, demonstrating initiative and commitment to driving results.
  >
  > **Action:**
  > Recognizing the importance of the project and its potential impact on our team's goals, I proactively approached my manager to express my interest in taking on the project. I highlighted how my skills and expertise could contribute to its success and how I was passionate about the project's objectives.
  >
  > I outlined a plan for how I would approach the project, including conducting research, gathering requirements, and collaborating with relevant stakeholders. I also proposed a timeline for completing key deliverables and milestones to ensure alignment with project objectives and deadlines.
  >
  > Upon receiving approval from my manager, I immediately began working on the project, dedicating additional time and effort to ensure its success. I took ownership of the project, coordinating with cross-functional teams, resolving issues, and driving progress towards achieving our goals.
  >
  > **Result:**
  > By volunteering to take on the project instead of waiting to be assigned, I demonstrated ownership, initiative, and a bias for action. The project was completed successfully within the established timeline and budget, and it made a significant contribution to our team's objectives.
  >
  > The experience reinforced the importance of taking initiative and seizing opportunities to make a difference. By volunteering for projects aligned with our team's goals, I was able to drive results, showcase my capabilities, and contribute to our overall success.



## 3. Leadership Principle 

### 3.1 Customer Obsession - 一切以客户为中心

- **Leaders start with the customer and work backwards.** **They work vigorously to earn and keep customer trust.** **Although leaders pay attention to competitors, they obsess over customers.**

  领导者**聚焦客户**，并从客户需求开始，逆向努力，打造产品。他们**积极工作**，赢得并保持客户信任。尽管领导者会关心竞争者，**但他们对客户的关注度更高。**

- **考点**
  你的故事中是否体现了你能从客户的角度出发思考并解决问题。在这个层面上，面试官希望大家能抱有**同理心。**
  客户既可以是真正的end user，也可以是你下游consume你的data/signal/pipeline的team。

- **准备**
  回顾你的工作，你有没有为客户解决过什么问题？是你主动发现并且解决的？客户的反馈怎么样？你跟客户之间如何交流，交流的结果怎么样？. 

### 3.2 Are Right, A Lot – 领导者要正确决策

- **Leaders are right a lot. They have strong judgment and good instincts. Theyseek diverse perspectives and work to disconfirm their beliefs.**

  领导者在大多数情况下都能做出**正确的决定**。他们拥有卓越的业务判断能力和敏锐的直觉。他们集思广益，并敢于纠正自己错误的信念。

- **考点**
  在信息不够且又必须做决定的情况下，你是如何做出正确的决定的？当时是否有死线压力，你如何从不同角度收集重要信息，怎么做出相对明智的决定？

- **准备**
  回想一下你工作当中是否有过必须要做决定但是又没有足够信息的情况，你是怎么做的？你为什么做了那个决定？虽然没有足够信息，你如何确定那些信息是做决定必须的，那些是不必须的，你如何收集那些必要的信息的？

### 3.3 Ownership – 主人翁精神

- **Leaders are owners. They think long term and don’t sacrifice long-term value for short-term results. They act on behalf of the entire company, beyond just their own team. They never say "that's not my job."**
- **考点**
  - 这个LP非常straightforward，就是考察你对日常工作的负责任和态度，不论这个工作是不是你的work，你都乐意dive in，发现问题，尝试解决问题。
  - 还有就是你知道how to trade-off between short-term gain and long-term gain。
  - 通常你需要讲一个在你team/scope/responsibility之外的故事，并且一定要重点强调，这个事情为什么非要由你而不是owner来做（比如当时事态紧急，owner又休假了，所以你来顶上了）。
  - 最后你要focus on impact，量化impact。
- **准备**
  回想一下你过去跟别的team一起collaborate ship一个大feature的时候，有没有需要你顶上去修别的team bug的情况？或者你自己发现了别的team的feature的dsat，你如何dive in，分析问题，最后解决的？ 

### 3.4 Bias For Action – 崇尚行动

- Speed matters in business. Many decisions and actions are reversible and do not need extensive study. We value calculated risk taking.

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

### 3.6 Invent & Simplify

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

### 3.9 Insist On The Highest Standards – 坚持最高标准

- Leaders have relentlessly high standards — many people may think these standards are unreasonably high. Leaders are continually raising the bar and drive their teams to deliver high quality products, services, and processes. Leaders ensure that defects do not get sent down the line and that problems are fixed so they stay fixed.

  领导者有着坚持不懈的高标准——许多人可能认为这些标准高得不合理。领导者不断提高标准，推动他们的团队提供高质量的产品、服务和流程。领导者保持关注直到问题解决，以确保缺陷不会被传递到生产线上。

- **考点**

  - 考察你如何set and achieve goals that are challenging, and solve it realistically.
  - 拆解为，首先你的goal是什么， 它有多challenging，你有什么计划能够achieve this goal，你的计划有哪几步，每个步骤设置的realistic吗？

- **准备**

  - 想一想你工作中的big project，complex project，你是怎么tackle这些project的，你给这些project定制的metrics是realistic还是audacious？你怎么achieve这些metrics的？怎么拆解项目变成小component的？. 1

### 3.10 Earn Trust – 获取信任

- Leaders listen attentively, speak candidly, and treat others respectfully. They are vocally self-critical, even when doing so is awkward or embarrassing. Leaders do not believe their or their team’s body odor smells of perfume. They benchmark themselves and their teams against the best.

  领导者认真聆听，坦率发言，尊重他人。即使让自己尴尬和窘迫，他们也会坦率的自我批评。领导者并不认为自己和自己的团队是永远正确的。他们向最好的团队对齐，并以此来检测自己和自己的团队。

- **考点**

  - 你肯定是要赢得别人的信任，所以这个LP考察的是你interpersonal skills，重点是考察你如何跟别人behavior和communicate。
  - 沟通是重点，遇到问题的时候，一定要有full picture，能够理解双方立场（要有同理心），知道问题从何而来，考虑到各自立场的pros and cons，以及提出plan来resolve conflict。

- **准备**

  - 你工作中肯定遇到过一些项目的讨论，有时候就是会有一些conflict的。比如有些时候是优先级的conflict，有些时候是metrics的conflict，有些时候是expectation的conflict，等等。想想你是如何处理这些conflict从而赢得信任的，你是如何resolve别人的concern的

### 3.11 Deliver Results – 达成业绩

- Leaders focus on the key inputs for their business and deliver them with the right quality and in a timely fashion. Despite setbacks, they rise to the occasion and never settle.

  领导者会关注其业务的关键决定条件，确保工作质量并及时完成。尽管遭受挫折，领导者依然勇于面对挑战，从不气馁。永远不要轻易屈从或妥协于任何事情，直到最终获得最好的结果。

- **考点**

  - 你的故事要体现你在面对挑战和不确定的时候，如何unblock你自己，并且deliver high quality work的。

- **准备**

  - 我们每个人都完成过项目，你回想这些项目从plan到deliver的整个过程中，遇到了哪些挫折和挑战，你是如何unblock你自己的？

### 3.12 Hire and Develop the Best – 雇用和培养最好的员工

- **Leaders raise the performance bar with every hire and promotion. They recognize exceptional talent, and willingly move them throughout the organization. Leaders develop leaders and take seriously their role in coaching others. We work on behalf of our people to invent mechanisms for development like Career Choice.**

  领导者在每次招聘和晋升时都会提高业绩标准。他们会发现杰出的人才，并愿意在整个公司内给予他们新的职责并加以历练。领导者培养领导者，他们认真指导下属，并以身作则。他们切身为员工着想，并努力帮助员工打造最好的职业发展平台

- 考点

  - 人们应该愿意为同龄人或加入公司的新亚马逊人开发技能做出贡献。领导者应该在整个组织中调动人才，同时牢记业务的成功以及员工的成长和发展。

- 准备

  - 想一下自己帮助过的人

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

### 3.15 Strive to be Earth’s Best Employer – 努力成为地球上的最佳雇主

- Leaders work every day to create a safer, more productive, higher performing, more diverse, and more just work environment. They lead with empathy, have fun at work, and make it easy for others to have fun. Leaders ask themselves: Are my fellow employees growing? Are they empowered? Are they ready for what’s next? Leaders have a vision for and commitment to their employees’ personal success, whether that be at Amazon or elsewhere.

  领导者每天都在努力创造一个更安全、更有生产力、更高绩效、更多样化和更公正的工作环境。他们用同理心来领导，在工作中获得乐趣，并让别人也享受工作的乐趣。领导者会问自己：我的员工在成长吗？他们获得足够的支持和鼓励吗？他们为下一步的工作做好准备了吗？领导者对员工的个人成功有愿景和承诺，无论是在亚马逊还是其他地方。

### 3.16 Success and ScaleBring Broad Responsibility – 成功和规模带来更大责任

- We started in a garage, but we’re not there anymore. We are big, we impact the world, and we are far from perfect. We must be humble and thoughtful about even the secondary effects of our actions. Our local communities, planet, and future generations need us to be better every day. We must begin each day with a determination to make better, do better, and be better for our customers, our employees, our partners, and the world at large. And we must end every day knowing we can do even more tomorrow. Leaders create more than they consume and always leave things better than how they found them.

  我们从一个车库起家，但我们已经不在那里了。我们是大型公司，我们影响着世界，而且我们远非完美。我们必须谦虚，对我们所有行为的影响也要深思熟虑。我们的社区、地球和后代需要我们每天都做得更好。我们必须以一种决心开始每一天，那就是为我们的客户、我们的员工、我们的合作伙伴，以至于整个世界做得更多，做得更好。亚马逊明白，随着他们的贡献和影响力的增长，员工也必须注意他们的决定对更多受众的影响。无论领导者做什么，都应该负起责任。





