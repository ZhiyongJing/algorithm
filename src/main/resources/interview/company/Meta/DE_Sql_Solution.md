1. Sql Solution

   > SQL：
   >
   > Table: `books`
   >
   > | Column               | Type     | Description              |
   > | -------------------- | -------- | ------------------------ |
   > | **book_id**          | INT (PK) | Primary key              |
   > | **title**            | VARCHAR  | Book title               |
   > | **author_id**        | INT (FK) | Foreign key to `authors` |
   > | **publication_date** | DATE     | Date of publication      |
   > | **category**         | VARCHAR  | Book category            |
   > | **price**            | DOUBLE   | Book price               |
   >
   > Table: `authors`
   >
   > | Column          | Type     | Description         |
   > | --------------- | -------- | ------------------- |
   > | **author_id**   | INT (PK) | Primary key         |
   > | **first_name**  | VARCHAR  | Author's first name |
   > | **last_name**   | VARCHAR  | Author's last name  |
   > | **birthday**    | DATE     | Author's birth date |
   > | **website_url** | VARCHAR  | Author's website    |
   >
   > Table: `transactions`
   >
   > | Column               | Type     | Description                        |
   > | -------------------- | -------- | ---------------------------------- |
   > | **transaction_id**   | INT (PK) | Primary key                        |
   > | **book_id**          | INT (FK) | Foreign key to `books`             |
   > | **customer_id**      | INT (FK) | Foreign key to `customers`         |
   > | **payment_amount**   | DOUBLE   | Total payment amount               |
   > | **book_count**       | INT      | Number of books in the transaction |
   > | **tax_rate**         | DOUBLE   | Applicable tax rate                |
   > | **discount_rate**    | DOUBLE   | Discount rate applied              |
   > | **transaction_date** | DATE     | Date of the transaction            |
   > | **payment_type**     | VARCHAR  | Payment method (e.g., credit card) |
   >
   > Table: `customers`
   >
   > | Column                       | Type     | Description                              |
   > | ---------------------------- | -------- | ---------------------------------------- |
   > | **customer_id**              | INT (PK) | Primary key                              |
   > | **first_name**               | VARCHAR  | Customer's first name                    |
   > | **last_name**                | VARCHAR  | Customer's last name                     |
   > | **registration_date**        | DATE     | Date of registration                     |
   > | **interested_in_categories** | VARCHAR  | Categories the customer is interested in |
   > | **is_rewards_member**        | BOOLEAN  | Whether the customer is a rewards member |
   > | **invited_by_customer_id**   | INT (FK) | Referring customer ID (if any)           |
   >
   > 1. 找出写了超过 3 本书的 `author_id`，按书籍数量排序
   >
   >    > ```sql
   >    > SELECT author_id, count(*) as book_count from books group by author_id having count(*) > 3 order by book_coun() DESC
   >    > ```