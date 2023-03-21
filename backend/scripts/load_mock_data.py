import csv
import os
import mysql.connector
import random

# Credentials
connection = mysql.connector.connect(
    host="localhost",
    user="takeyuki.oshima",
    password="password",
    database="joole_marketplace"
)

cursor = connection.cursor()

###### Load data from CSV files into the corresponding tables ######
tables = [
    "user",
    "product",
    "product_type",
    "technical_detail",
    "description"
]

for table in tables:
    filename = f"../src/main/resources/db/data/{table}_mock_data.csv"
    
    # Check if file exists
    if not os.path.isfile(filename):
        print(f"File '{filename}' not found. Skipping this table.")
        continue

    with open(filename, "r") as csvfile:
        csv_data = csv.reader(csvfile)

        header = next(csv_data)
        num_columns = len(header)
        insert_query = f"INSERT INTO {table} ({', '.join(header)}) VALUES ({', '.join(['%s'] * num_columns)})"

        for row in csv_data:
            cursor.execute(insert_query, row)
                
    # Commit changes
    connection.commit()
    print(f"Data from '{filename}' has been loaded into the '{table}' table.")


###### Generate project entries based on users table ######
select_query = "SELECT user_name FROM user WHERE user_type = 'customer'"
cursor.execute(select_query)

customer_user_names = [row[0] for row in cursor.fetchall()]
for user_name in customer_user_names:
    num_projects = random.randint(1, 2)
    insert_query = "INSERT INTO project (user_name) VALUES (%s)"

    for _ in range(num_projects):
        row = (user_name,)
        cursor.execute(insert_query, row)

    # Commit changes
    connection.commit()
    print(f"Inserted {num_projects} project(s) for user '{user_name}' into 'project' table.")


###### Generate project_product entries based on project table and product table ######
select_query = "SELECT project_id FROM project"
cursor.execute(select_query)
project_ids = [row[0] for row in cursor.fetchall()]

select_query = "SELECT product_id FROM product"
cursor.execute(select_query)
product_ids = [row[0] for row in cursor.fetchall()]

insert_query = "INSERT INTO project_product (project_id, product_id) VALUES (%s, %s)"
num_products_choices = [20, 30, 40]

for project_id in project_ids:
    num_products = random.choice(num_products_choices)

    for product_id in random.sample(product_ids, num_products):
        row = (project_id, product_id)
        cursor.execute(insert_query, row)

    # Commit changes
    connection.commit()
    print(f"Inserted {num_products} products for project '{project_id}' into 'project_product' table.")


# Close the connection
cursor.close()
connection.close()
