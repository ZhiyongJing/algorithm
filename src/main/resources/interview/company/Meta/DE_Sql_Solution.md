1. Sql Solution

   > SQL：
   >
   > Table: `books`
   >
   > | Column                     | Type     | Description                |
   > | -------------------------- | -------- | -------------------------- |
   > | **book_id**          | INT (PK) | Primary key                |
   > | **title**            | VARCHAR  | Book title                 |
   > | **author_id**        | INT (FK) | Foreign key to `authors` |
   > | **publication_date** | DATE     | Date of publication        |
   > | **category**         | VARCHAR  | Book category              |
   > | **price**            | DOUBLE   | Book price                 |
   >
   > Table: `authors`
   >
   > | Column                | Type     | Description         |
   > | --------------------- | -------- | ------------------- |
   > | **author_id**   | INT (PK) | Primary key         |
   > | **first_name**  | VARCHAR  | Author's first name |
   > | **last_name**   | VARCHAR  | Author's last name  |
   > | **birthday**    | DATE     | Author's birth date |
   > | **website_url** | VARCHAR  | Author's website    |
   >
   > Table: `transactions`
   >
   > | Column                     | Type     | Description                        |
   > | -------------------------- | -------- | ---------------------------------- |
   > | **transaction_id**   | INT (PK) | Primary key                        |
   > | **book_id**          | INT (FK) | Foreign key to `books`           |
   > | **customer_id**      | INT (FK) | Foreign key to `customers`       |
   > | **payment_amount**   | DOUBLE   | Total payment amount               |
   > | **book_count**       | INT      | Number of books in the transaction |
   > | **tax_rate**         | DOUBLE   | Applicable tax rate                |
   > | **discount_rate**    | DOUBLE   | Discount rate applied              |
   > | **transaction_date** | DATE     | Date of the transaction            |
   > | **payment_type**     | VARCHAR  | Payment method (e.g., credit card) |
   >
   > Table: `customers`
   >
   > | Column                             | Type     | Description                              |
   > | ---------------------------------- | -------- | ---------------------------------------- |
   > | **customer_id**              | INT (PK) | Primary key                              |
   > | **first_name**               | VARCHAR  | Customer's first name                    |
   > | **last_name**                | VARCHAR  | Customer's last name                     |
   > | **registration_date**        | DATE     | Date of registration                     |
   > | **interested_in_categories** | VARCHAR  | Categories the customer is interested in |
   > | **is_rewards_member**        | BOOLEAN  | Whether the customer is a rewards member |
   > | **invited_by_customer_id**   | INT (FK) | Referring customer ID (if any)           |
   >
   > ```sql
   > -- Active: 1735699303502@@127.0.0.1@3306@meta
   >
   > -- 1. Different payment type total sales (Top 3 by sales value)
   > select payment_type,
   >  sum(payment_amount) as total_sales
   >  from transactions
   > group by payment_type
   > ORDER BY total_sales DESC
   > limit 3
   >
   > -- 2. Customers who purchased books from the same author with at least two categories and their total sales
   >
   > with temp as (select t.customer_id, 
   > sum(t.payment_amount) as total_sales,
   > count(DISTINCT b.category) as total_category
   > from transactions  t
   > left join  customers c on c.customer_id = t.customer_id
   > left join books b on t.book_id = b.book_id
   > group by customer_id
   > )
   > select customer_id, total_sales, total_category from temp where temp.total_category >=1
   >
   > -- 3. Rank customers by the sales of those they invited
   > -- 有一个customers是被别人invite来的， 找top 3 invite别人的customer（top 指的是交易额）
   >
   > select c.invited_by_customer_id as customer_id, sum(t.payment_amount) as total_sales
   > from customers c
   > inner join transactions t on c.customer_id = t.customer_id
   > where c.invited_by_customer_id is not null
   > group by c.invited_by_customer_id
   >
   >
   > -- 4. Total and unique books purchased by each customer
   > select customer_id,
   > count(DISTINCT book_id) as books
   > from transactions
   > group by customer_id
   >
   > -- 5. Authors with URLs starting with "https//" but no sales
   >
   > select a.author_id
   > from authors a
   > left join books b on a.author_id = b.author_id
   > left join transactions t on b.book_id = t.book_id
   > where a.website_url LIKE 'https://%'
   > and transaction_id is null;
   >
   > -- 6. Top unique customers who made purchases this and the previous month
   > select MONTH(transaction_date), MONTH(CURRENT_DATE())
   > from transactions t1
   > inner join transactions t2 on t1.customer_id = t2.customer_id
   >
   > WHERE MONTH(transaction_date) = MONTH(CURRENT_DATE())
   >
   > -- 7. Authors who wrote more than 3 books, ordered by book count
   > select author_id,
   > count(book_id) as books_count
   > from books
   > GROUP BY author_id
   > having count(author_id) > 3
   > ORDER BY count(book_id) desc
   >
   > -- 8. Percentage of customers who made a purchase on their registration date
   >
   > select 
   >     sum(case 
   >             when (t.transaction_date = c.registration_date) then 1 
   >             else 0 
   >         end) / count(DISTINCT c.customer_id) 
   > from customers c
   > left JOIN transactions t on t.customer_id = c.customer_id
   >
   > -- 9. Total books purchased by customers on their first and last purchase day (for customers with more than 1 purchase day and >3 books in total)
   > select customer_id, max(transaction_date) as last_day, min(transaction_date) as first_day
   > from transactions
   > GROUP BY customer_id
   > HAVING count(DISTINCT transaction_date) >= 1 and count(DISTINCT book_id) >= 1
   >
   > -- 10. Percentage of customers who never purchased books from their interested categories
   >
   > select subs(interested_in_categories, ',') from customers
   > select *
   > sum(case when c.customer_id = t.customer_id and t.book_id = t.book_id and b.category in STRING_TO_ARRAY())
   > from customers c
   > left join transactions t on c.customer_id = t.customer_id
   > left join books b on b.book_id = t.book_id
   >
   >
   >
   > -- Get the sum and distinct count of the particular payment type.
   > select payment_type, sum(payment_amount), COUNT(1)
   > from transactions
   > GROUP BY payment_type
   >
   >
   > ```
   > fd
   >
2. Python solution

   > 1. given int，find smallest number from all odd number
   > 1. 
   >
