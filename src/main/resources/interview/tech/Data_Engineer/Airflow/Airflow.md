# Airflow 

- 介紹時不會與其他ETL和workflow management的Tool來去做比較。
- 此介紹僅包含Airflow的一部分，並非全貌，因此看完這個介紹以後，Airflow還有很多東東等著你去發掘呢!
- 人生第一次寫文章，寫太爛請多包涵。

# Introduction

[Airflow官方文件](https://airflow.apache.org/docs/stable/)

## 基本資料:

- From Airbnb，現為Apache頂級專案
- 程式語言: python
- 是個工作流程管理系統
- 有很棒的GUI
- 開源
- 遵循著”configuration as code”原則，workflow及task都用python寫

## 簡介(From Leemeng)

- Airflow是以Python寫成的工作流程管理系統（Workflow Management System）
- 近年不管是資料科學家、資料工程師還是任何需要處理數據的軟體工程師，Airflow 都是他們用來建構可靠的 ETL 以及定期處理批量資料的首選之一。
- 大致UI長相:
  - ![img](https://i.imgur.com/mr4Gn60.png)
  - ![img](https://i.imgur.com/Hf9f5or.gif)
- 大致結構:
  - ![img](https://i.imgur.com/q2Fxw6k.png)
  - Workers: 負責執行task的工作單位
  - Scheduler: 負責排task給Workers，以及即時回報排程狀況給Webserver
  - Database: 上游task裡能將執行結果存到Database，下游結果能從Database裡取得上游task的結果
  - Webserver: 接收Scheduler以及task的logs來顯示UI
- 基本上屌打其他寫workflow的東西(書是這樣講的)。
- ![img](https://i.imgur.com/15fqBA9.png)

## DAG(Directed Acyclic Graphs)

- workflow 可以被視為一個graph的結構, tasks為nodes，而task dependencies為 directed edges between nodes.

- 而在Airflow裡，一個workflow是一個DAG(Directed Acyclic Graph), 其graph不能有cycle。

- ![img](https://i.imgur.com/x0eCkHn.png)

- 幾種設定task的Dependency的語法(之後還會再看到)

  ```
  # 上到下游
  op1 >> op2 >> op3
  # or
  op1.set_downstream(op2)
  op2.set_downstream(op3)
  
  # 下到上游
  op3 << op2 << op1
  # or
  op2.set_upstream(op1)
  op3.set_upstream(op2)
  
  # 也可以到一個list
  op1 >> [op2, op3]
  ```

## 大致功能:

- 為workflow工作排程、處理task dependencies, 平行執行, error handling, etc.
- Airflow可以連到、執行各種不同語言、系統的tasks
- 若是把某個task更新後，通過backfilling能把過去workflow的執行的狀態的該task與其下游task都重新執行一遍。
- ![img](https://i.imgur.com/Kimwj0C.png)

# 入門

## Requirements

- 作業系統: Linux, MacOS, WSL(Windows Subsystem for Linux)
- 有pip
- 其實也可以用docker啦，不過這篇文不會教你怎麼用docker來跑Airflow。

## 安裝

```
# 安裝
pip install apache-airflow

# 若有其他相依套件要安裝(像mongo)，可以:
pip install apache-airflow[mongo]
```

## 第一次執行

```
# 設定Airflow至當前路徑
export AIRFLOW_HOME=$(pwd)

# 產生config檔及基本設定
airflow initdb

# 打開webserver
airflow webserver -p 8080
```

接著在瀏覽器打開`localhost:8080`，就可以看到以下畫面
![img](https://i.imgur.com/r8yB3jC.png)

- 圖中的example_xxxx是官方提供的DAG範例，看著他們的Code也可以學習到一些東西唷~!

- 若是不想看到examples，需更改airflow.cfg的內容，將

  ```
  load_examples
  ```

  設為False，接著執行

  ```
  # 重製設定
  airflow resetdb
  # 打開Web Server
  airflow webserver -p 8080
  ```

  ```
  resetdb
  ```

  的操作會將之前所設定的Connections、Pool給清掉，因此最好一開始就先改好airflow.cfg

- 打開webserver後，再開一個cmd，執行

  ```
  export AIRFLOW_HOME=$(pwd)
  # 開啟排程器
  airflow scheduler
  ```

  再來在UI上點選隨便一個

  

  將其設為ON，scheduler即會將其排程(若時間到的話)，若你等不及了想執行(測試)看看，

  

  點選圖中最左邊符號 

  trigger

  ，即可人工trigger一個dag，將其執行。

- 這裡講一下scheduler與webserver的關係，在沒有開啟scheduler的情況下，webserver會每過一段時間(多久我不知道，有時候根本不會動)從`$AIRFLOW_HOME/dags`底下看dags的狀況，若有更新或新增便會更新WebUI的Dags。而在開啟scheduler的狀況下，則會即時更新。

> **WebUI畫面並非即時，需要重新整理畫面才會看到新的狀態。**

## 程式基本架構

### 範例

1. 首先將這個程式碼放到

   ```
   $AIRFLOW_HOME/dags
   ```

   底下。

   ```
   import os
   import time
   import logging
   from datetime import datetime, timedelta
   from airflow import DAG
   from airflow.operators.python_operator import PythonOperator, BranchPythonOperator
   from airflow.operators.dummy_operator import DummyOperator
   
   
   def check_weekday(date_stamp):
       today = datetime.strptime(date_stamp, '%Y%m%d')
   
       if today.isoweekday() <= 5:
           return 'is_workday'
       else:
           return 'is_holiday'
   
   
   def get_metadata():
       logging.info('get_metadata'+'~'*30+'!!!')
   
   
   def clean_data():
       logging.info('clean_data'+'~'*30+'!!!')
   
   
   default_args = {
       'owner': 'AxotZero',
       'start_date': datetime(2020, 8, 21),
       'schedule_interval': '@daily',
       'retries': 2,
       'retry_delay': timedelta(minutes=5)
   }
   
   with DAG(dag_id='tutorial', default_args=default_args) as dag:
   
       tw_stock_start = DummyOperator(
           task_id='tw_stock_start'
       )
   
       check_weekday = BranchPythonOperator(
           task_id='check_weekday',
           python_callable=check_weekday,
           op_args=['{{ ds_nodash }}']
       )
   
       is_holiday = DummyOperator(
           task_id='is_holiday'
       )
   
       is_workday = DummyOperator(
           task_id='is_workday'
       )
   
       get_metadata = PythonOperator(
           task_id='get_metadata',
           python_callable=get_metadata,
       )
   
       clean_data = PythonOperator(
           task_id='clean_data',
           python_callable=clean_data,
       )
   
       tw_stock_end = DummyOperator(
           task_id='tw_stock_end',
           trigger_rule='one_success'
       )
   
       tw_stock_start >> check_weekday >> [is_workday, is_holiday]
       is_holiday >> tw_stock_end
       is_workday >> get_metadata >> clean_data >> tw_stock_end
   ```

2. 若你的webserver和scheduler有開啟的話，即可在你的web上看到一個叫做tutorial的dag。![img](https://i.imgur.com/dMFDnUd.png)

3. 這個簡單的程式碼能產生以下的workflow
   ![img](https://i.imgur.com/AyjUhTw.png)

4. 打開Dag旁邊的開關，即可執行~!

### 介紹

```
這個dag的功能: 
    1. 一開始先判斷execution_date是假日還是平日，若是平日則跳到2，若為假日跳到3
    2. logger print出一些資訊，跳到3
    3. 結束。
```

1. 我們先看到以下程式碼片段，

   ```
   default_args = {
       'owner': 'AxotZero',
       'start_date': datetime(2020, 8, 21),
       'schedule_interval': '@daily',
       'retries': 2,
       'retry_delay': timedelta(minutes=5)
   }
   
   with DAG(dag_id='tutorial', default_args=default_args) as dag:
       ...
   ```

   - 這個default_args除了拿來當參數給dag以外，也會作為參數傳給該dag底下的各個operator。因此這個default_dags其實包含了dag和operator的參數。
   - 給dag的
     - owner: 就擁有者
     - start_date: 該dag開始的執行時間(可以是過去的時間，會從過去的時間點開始執行)
     - schedule_interval: 多久執行一次
   - 給operator
     - retries: 執行失敗時，要重試多少次
     - retry_delay: 執行失敗時，過多久才重試

   > *dag和operator還有一大堆參數等著你去發掘唷~*
   > dag: [連結](https://airflow.apache.org/docs/stable/_api/airflow/models/dag/index.html)
   > operator: [連結](https://airflow.apache.org/docs/stable/_api/airflow/models/baseoperator/index.html)

2. 再看到以下程式碼片段

   ```
       tw_stock_start = DummyOperator(
           task_id='tw_stock_start'
       )
   
       check_weekday = BranchPythonOperator(
           task_id='check_weekday',
           python_callable=check_weekday,
           op_args=['{{ ds_nodash }}']
       )
   
       is_holiday = DummyOperator(
           task_id='is_holiday'
       )
   
       is_workday = DummyOperator(
           task_id='is_workday'
       )
   
       get_metadata = PythonOperator(
           task_id='get_metadata',
           python_callable=get_metadata,
       )
   
       clean_data = PythonOperator(
           task_id='clean_data',
           python_callable=clean_data,
       )
   
       tw_stock_end = DummyOperator(
           task_id='tw_stock_end',
           trigger_rule='one_success'
       )
       
       tw_stock_start >> check_weekday >> [is_workday, is_holiday]
       is_holiday >> tw_stock_end
       is_workday >> get_metadata >> clean_data >> tw_stock_end
   ```

   - 一個operator其實就是一個task
   - task之間的dependency可以用 `>>` 和 `<<`符號來定義
   - 其實有一大堆operator，舉幾個例子
     - 用來執行python程式碼的 PythonOperator
     - 用來執行shell script的 BashOperator
     - 等待外部DAG的某個task執行完的 ExternalTaskSensor
     - … 超級多的啦

   > *還有更多operator等著你去發掘*
   > 官方提供的operator: [連結](https://airflow.apache.org/docs/stable/_api/airflow/operators/index.html)
   > contributer們提供的operator: [連結](https://airflow.apache.org/docs/stable/_api/airflow/contrib/operators/index.html)
   > ***operator其實也可以自己寫，把程式碼抽象化。***

   - 在第44行大家可以看到

     ```
     op_args=['{{ ds_nodash }}']
     ```

     其中

     ```
     '{{ds_nodash}}'
     ```

     其實是macro，其代表"YYYYMMDD"格式的dag執行時間。

   > 關於更多的macro請看這裡: [連結](https://airflow.apache.org/docs/stable/macros-ref.html)

# UI介紹

> 以下所說的各種功能，絕大部分都能通過程式碼或指令達成。
> 不過我很笨，我只會用UI

## DAGs

![img](https://i.imgur.com/Em4swmF.png)

1. 在經過剛剛的排程執行過後，自己先點點看DAG頁面的東西吧~
2. 唯一比較需要注意的是最左邊的紅色按鈕`Delete Dag`，會將過去DAG Runs的紀錄砍掉，若該DAG依然存在且Scheduler還開著，便代表要將這個DAG從頭重跑一遍。

**點進DAG裡能夠看到下面這一行，皆是在表達該DAG的執行狀況或設定**
![img](https://i.imgur.com/zIex3m5.png)

### Tree View

![img](https://i.imgur.com/JibXBLw.png)

- Pros: 能看到各個時間點該Dag的執行狀況(最右邊)
- Cons: 圖較難理解

### Graph View

![img](https://i.imgur.com/entCSRd.png)

- Pros: 圖好理解
- Cons: 一次只能一個時間點的執行狀況

### Task

![img](https://i.imgur.com/l71wHny.png)

- 若是點擊了Workflow底下的Task，能跑出以下頁面，
- `Zoom into SubDAG` : 只有在該Operator為SubDagOperator時才會出現，其代表這個Operator會執行一個Dag，範例請參考[連結](https://github.com/AxotZero/Airflow-MongoDB/tree/master/TW-Stock-practice/dags)**
- `Task Instances Details` : 跟你說這個Task的Detail。
- `Rendered` 又名 `Rendered Template` : Template其實就是你自行想執行的其他語言的程式碼，如sql, hql…。如下圖![img](https://i.imgur.com/93MmPa6.png)

> ***關於Template的設定，後面會更細節討論***

- `View Log` : 就看該Task的Log

- 下面有四個Function分別是Run, Clear, Mark Failed, Mark Sucess，其中

  - ```
    Run
    ```

     直接執行這個Task

    - `Ignore Task State` : 代表不論他的State如何就直接執行他(Default只有無狀態的Task才能被執行)
    - `Ignore Task Deps` : 代表不論該Task的上游執行了沒，就直接執行該Task
    - `Ignore All Deps` : 以上皆是

  - ```
    Clear
    ```

    ，將Task狀態清空，讓Scheduler能重新將該Task丟去排程，也就是所謂的BackFilling，當你Task的程式碼有所變動且想重新執行時，這就是個方便好用的東西。

    - `Past`，每個DAG會有他被執行的時間(execution time)，每個DAG執行過後會產生此task的task instance，將你所選的Task Instance所有之前(含自己)執行過的TaskInstance的狀態清掉。
    - `Future`，跟上面一樣，不過是未來所有的Task Instance。
    - `Upstream`，就上游的task。
    - `Downstream`，就下游的task。
    - `Recursive`，須和Upstream或Downstream配合使用，將該task所有上游或下游task清掉。
    - `Failed`，只清掉state為fail的task。
      以上六點可自行組合，假設我勾選了Past, Future, Downstream, Recursive -> 則代表將所有DAG的該Task和其下游全部清掉。

  - `Mark Failed`和`Mark Success`，就只是標記而已，沒有其他功能。

## Browse

![img](https://i.imgur.com/64iuhfS.png)
查看你過去工作狀況的地方

### SLA Misses

![img](https://i.imgur.com/1OMTjEv.png)

- SLA Misses是通常用於連線逾時時，寄信通知用的。

- 而這裡只是紀錄了過去SLA Misses發生的狀況。

- 如何設置SLA Misses?

  1. 在你的airflow.cfg設定你的email設定

  ```
  [smtp]
  smtp_host = smtp.gmail.com
  smtp_starttls = True
  smtp_ssl = False
  smtp_user = YOUR_EMAIL_ADDRESS
  smtp_password = 16_DIGIT_APP_PASSWORD
  smtp_port = 587
  smtp_mail_from = YOUR_EMAIL_ADDRESS
  ```

  1. 在你的cli執行

     ```
     # 使你的airflow得到新的config設定
     airflow resetdb
     ```

  2. 在你的Operator裡設定 

     ```
     sla
     ```

      參數

     ```
     mid = BashOperator(
         task_id='mid',
         sla=timedelta(seconds=5),
         bash_command='sleep 10',
         retries=0,
     )
     ```

     - 一旦時間超過sla的timedelta，就會寄信囉~
     - 若是沒有設定email也沒關係，就只是不會寄信而已，不過在SLA Misses的地方還是會有紀錄唷~

### Task Instances

![img](https://i.imgur.com/1veYurG.png)

- 過去所有Task執行的狀況，在這裡可以做搜尋、更改狀態…等。

### Dag Runs

![img](https://i.imgur.com/cvweo0n.png)

- 過去所有Dags執行的狀況，在這裡可以做搜尋、更改、刪除狀態…等。

> ***關於Browse裡的Jobs和Logs我自己也不知道他是幹嘛的…。***
> **TO BE DONE.**

## Admin

![img](https://i.imgur.com/AVdfQJb.png)

### Pools

![img](https://i.imgur.com/bc1mpGY.png)

- 資源控管用的變數，能夠限定執行的operator數量。

- 假設你希望你的postgres_db同一時間只能接受5個operator連接，那Pool就可以派上用場了

  1. 先在UI上新增一個Pool `postgres_dwh`
     ![img](https://i.imgur.com/bNLJw1K.png)

  2. 在operator設定pool參數

     ```
     extract_product = MyPostgresOperator(
         sql='select_product.sql'
         task_id='extract_product',
         pool='postgres_dwh')
     ```

     其中第四行的 

     ```
     pool='postgres_dwh'
     ```

     及代表將這個operator加入postgres_dwh的pool裡面。

### Connections

![img](https://i.imgur.com/V3sgWHo.png)

![img](https://i.imgur.com/TsK5hpi.png)

- 與外部平台連線用的變數
- 在後面的Hook介紹裡面會再提到。

### Variables

![img](https://i.imgur.com/rCavyZm.png)

- 單純的變數

- 在設定完上圖的sql_path之後，若是我想在程式裡面引用他，只需要:

  ```
  from airflow.models import Variable
  tmpl_search_path = Variable.get("sql_path")
  ```

> 在UI上設定Pool, Connection, Variable這三種變數的優缺點:
> 優: 設定一些機密的東西像Connection的設定能夠不用以明文的方式寫在程式碼裡面
> 缺: 要設定的東西太多的話會很累
> *這些設定其實也可以自行寫程式碼來設定，相關例子請看[這裡](https://github.com/gtoonstra/etl-with-airflow/blob/master/examples/etl-example/dags/init_etl_example.py)，比較方便，不過就要以明文寫在程式碼裡面*

### XCOM

![img](https://i.imgur.com/bfUh4VT.png)

- 前面有提到過，上游task會將執行結果存到database，下游task從database來取得其執行結果，這便是airflow上下游task溝通的方式，也就是所謂的Xcom

- 使用方式(以PythonOperator為例):

  1. 設定operator的參數 

     ```
     provide_context=True
     ```

     ```
     my_operator = PythonOperator(
         task_id='my_task',
         python_callable=my_func,
         provide_context=True
     )
     ```

  2. 在你所寫的functoin裡的使用方式

     ```
     def my_func(**context):
         ti = context['task_instance']
         
         # 得到上游的執行結果(key的default值為'return_value')
         previous_result = ti.xcom_pull(task_ids='previous_work')
         
         # 得到的新結果
         new_result = previous_result + 100
         
         # 存入Xcom(key='xcom_push')
         ti.xcom_push(key='xcom_push', value=new_result)
         
         # return 其實也會存到Xcom(key='return_value')
         return new_result
         
     ```

- 若是你是想讓別種Operator甚至別種語言來存取xcom怎麼辦? 若是你還記得前面曾提到過的macro的話，其實就是靠macro來達成這件事情唷!

  ```
  t2 = BashOperator(
      task_id='t2', 
      bash_command='echo "{{ ti.xcom_pull("t1") }}"')
  ```

  > 有關Xcom更多資訊，請看[這裡](https://airflow.apache.org/docs/stable/concepts.html?highlight=xcom)

  > ***有看過有人說若是資料太大的話，用這種方式會出錯，因此或許需要自行用別種方式來讓task溝通(我自己用過最大的資料是一個shape為 (13000\*20)&其值都是字串的dataframe，還沒有出錯過啦~~)***

### Configuration

拿來看你airflow.cfg用的

### Users

> 不知道有甚麼用，不過有一個東西叫做 ***Webserver-RBAC mode*** 可能有相關，也是近期才發現的，我自己目前只有碰一下，但還有去沒摸索。
> **TO BE DONE**

## Data Profiling

一個讓你用sql來快速檢視數據的地方。
![img](https://i.imgur.com/dS5Tecb.png)

### Ad Hoc Query

讓你以sql query來看數據的地方
![img](https://i.imgur.com/EEZS3aX.png)

### Charts

讓你用sql query + 額外的一些設定，產生圖表的地方

1. 進到這個頁面，選擇更改或create一個圖表
   ![img](https://i.imgur.com/KNAJmPn.png)
2. edit/create頁面太大我就不放了，只要按照他給的layout來寫你的sql query，即可產生以下圖表。
   ![img](https://i.imgur.com/9gebKcU.png)

### Events

> **我現在依然不知道Events是幹嘛用的。**
> **TO BE DONE.**

# 其他細項介紹

## Hook [連結](https://airflow.apache.org/docs/stable/_api/index.html#hooks)

- Airflow有提供一個叫Hook的東西，讓使用者可以先設定好Connections設定後，只需要在Hook裡丟conn_id，即可連線。
- 有哪些Hook以及有哪些function需要自行去看文件
  - [airflow.hooks](https://airflow.apache.org/docs/stable/_api/airflow/hooks/index.html)
  - [airflow.contrib.hooks](https://airflow.apache.org/docs/stable/_api/airflow/contrib/hooks/index.html)
- 以下以Mongodb為例
- ![img](https://i.imgur.com/mcBmv3B.png)

### UI上設定

1. 在webserver的上方找到Admin/Connections，並點擊
   - ![img](https://i.imgur.com/8L8kXjo.png)
2. 在該頁面找到你要用的conn_id(我們這裡是"mongo_default")，並點擊左邊鉛筆的圖案；或也可以自行建立一個conn_id，在最上方點Create。
   - ![img](https://i.imgur.com/tPR2t6e.png)
3. 到這頁面就可以自己改啦~~~
   - ![img](https://i.imgur.com/tjVwr7A.png)

### 如何使用?

- 給定Hook你的conn_id，剩下就看你要執行甚麼操作。

- [Mongodb文件連結](https://docs.mongodb.com/manual/)

- 範例:

  ```
  # convert to double
  convert_query = [{
  	"$set":{
  		col: {
  			'$convert':{
  				'input': "$"+col, 
  				'to': 'double', 
  				'onError': None
  			}
  		}
  	}
  }]
  MongoHook(conn_id='mongo_default').update_many(
  	filter_doc={},
  	update_doc=convert_query,
  	mongo_db=tmp_config['db'],
  	mongo_collection=tmp_config['collection']
  )
  ```

## Template

Template的功用通常是讓你執行已經寫好的其他程式語言的程式碼，如之前已經寫好的sql, hql…等，就不需要將一切都寫在airflow裡面。

### 我們直接看範例

1. 設定Operator要去哪裡找他的template，以及template的檔名。

   ```
   my_operator = MyPostgresOperator(
       sql='my_sql.sql',
       template_searchpath='/sql_dir',)
   ```

2. 前面說過了Operator可以自行定義了對吧，這裡就來順便看個範例Code吧。

   ```
   class MyPostgresOperator(BaseOperator):
   
       template_fields = ('sql', 'parameters')
       template_ext = ('.sql',)
       ui_color = '#ededed'
   
       @apply_defaults
       def __init__(
               self, sql,
               postgres_conn_id='postgres_default'
               *args, **kwargs):
           super(MyPostgresOperator, self).__init__(*args, **kwargs)
           self.sql = sql
           self.postgres_conn_id = postgres_conn_id
   
       def execute(self, context):
           logging.info('Executing: ' + str(self.sql))
           self.hook = PostgresHook(postgres_conn_id=self.postgres_conn_id)
           self.hook.run(self.sql)
   ```

   - 這個Operator很簡單，就是通過你給的連線設定連線，並執行你所提供sql檔案。

   - template_fields是會顯示在ui上的feature，可以自行設定

   - 若template_fields裡出現以template_ext結尾的字串，則會自動將該檔案檔名替代成其內容

     - 假設my_sql.sql的內容為

     ```
     SELECT * FROM my_table
     ```

     - 則MyPostgresOperator的init，會將 `sql="my_sql.sql"` 替換成 `sql="SELECT * FROM my_table"`

   - 你可以在UI上點擊有使用template的task，接著點擊Render便會出現以下類似的畫面
     ![img](https://i.imgur.com/FN9lvvB.png)

## Executor [連結](https://airflow.apache.org/docs/stable/executor/debug.html)

- Sequential Executor
  - default的Executor
  - 只能一次處理一個task
  - 遇到sensor的operator會直接卡住
- Debug Executor
  - 一次只能處理一個
  - 遇到sensor的operator會reschedule而不會卡住
  - 可以在config裡設定 'fail_fast'參數，只要遇到一個task fail，整個dag就fail，讓你好debug一點
- DaskExecutor
  - 支援cluster運算
  - Dask是一個python的平行運算library，主要用於大資料(Dataframe, numpy, etc)處理及分析，而cluster運算只是他的其中一個功能。
- LocalExecutor
  - 支援multithread運算，但依然只能在local端做
- CeleryExecutor
  - 支援cluster運算
  - Celery是一個用Python的分散式任務佇列(Distributed Task Queue)，通常以RabbitMQ或Redis做為其Broker [Celery介紹](https://codertw.com/程式語言/704976/)
  - Flower是airflow一個拿來監控Celery佇列的工具(也是一個server)
  - [Airflow CeleryExecutor 多台機器部署教學](https://blog.csdn.net/q383965374/article/details/104537121)
- KubernetesExecutor
  - 支援cluster運算
  - Kubernetes是一個能夠部署container的Tool，提供以下功能:
    - Deployment: 同時部署多個容器到多台機器上
    - Scaling: 服務的乘載量有變化時，可以對容器做自動擴展
    - Management: 管理多個容器的狀態，自動偵測並重啟故障的容器
    - [Kubernetes介紹](https://medium.com/@C.W.Hu/kubernetes-basic-concept-tutorial-e033e3504ec0)

> 關於在Airflow設定Executor的方法我自己也不是很清楚，只有試過別人寫好的Docker來去跑跑看
> LocalExecutor和CeleryExecutor:
>
> - https://github.com/puckel/docker-airflow
> - https://registry.hub.docker.com/r/puckel/docker-airflow

> docker似乎有一個叫docker swarm的東西，是做cluster用的，不過我還沒看
> ***關於Executor的使用方法，還需讀者自行Google看看。***

# 其他

## Airflow相關文章

- 入門
  [Airflow 動手玩](https://www.coderbridge.com/@FrankYang0529)
  [一段 Airflow 與資料工程的故事：談如何用 Python 追漫畫連載](https://leemeng.tw/a-story-about-airflow-and-data-engineering-using-how-to-use-python-to-catch-up-with-latest-comics-as-an-example.html)
- 進階
  [ETL-best-practice](https://gtoonstra.github.io/etl-with-airflow/index.html)

## Windows10 WSL(Windows Subsystem Linux)跑airflow的教程

[WSL安裝及將資料夾移動至C:的教學](https://medium.com/@chenwy0806/在-windows-10-上運行-ubuntu-linux-子系統並將系統移動至其他硬碟-57e8faaa3e04)
[WSL安裝airflow的教學](https://coding-stream-of-consciousness.com/2018/11/06/apache-airflow-windows-10-install-ubuntu/)
[WSL2+docker安裝](https://medium.com/@zex555.a198719/wsl2-docker-docker-compose環境架設記錄-429f4bf5d68a)

## Mongodb教程

[如何安裝及run mongodb](https://docs.microsoft.com/en-us/windows/wsl/tutorials/wsl-database#install-mongodb)
[pymongo](https://pypi.org/project/pymongo/)
[aggregate stage](https://docs.mongodb.com/manual/reference/operator/aggregation-pipeline/)
[aggregate operator介紹](https://docs.mongodb.com/manual/reference/operator/aggregation/)

Last changed by  

------

 

[JohnCena ](https://hackmd.io/@JohnCena)

·Follow



 0[ 9](https://hackmd.io/@JohnCena/Hki7kBTlP#) 19260



------

Published on **[ HackMD](https://hackmd.io/)**