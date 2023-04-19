import csv
import os
import mysql.connector
import random
import bcrypt

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
    # "user",
    "product",
    "product_type",
    "technical_detail",
    "description"
]

for table in tables:
    filename = f"./backend/src/main/resources/data/{table}_mock_data.csv"
    
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

# Update product_type_id, technical_detail_id, and description_id to match product_id in the product table
update_query = """
    UPDATE product p
    JOIN product_type pt ON p.product_id = pt.product_type_id
    JOIN technical_detail td ON p.product_id = td.technical_detail_id
    JOIN description d ON p.product_id = d.description_id
    SET p.product_type_id = pt.product_type_id,
        p.technical_detail_id = td.technical_detail_id,
        p.description_id = d.description_id
"""

cursor.execute(update_query)
connection.commit()
print("Updated product_type_id, technical_detail_id, and description_id to match product_id in the product table.")

# Create 2 new projects with username "test", project_name "Project 1" and username "test", "Project 2"
username = "test"

project_query = """
    INSERT INTO project (username, project_name)
    VALUES (%s, %s)
"""
project_names = ["Project 1", "Project 2"]
for project_name in project_names:
    cursor.execute(project_query, (username, project_name))
connection.commit()
print("Created 2 new projects with username 'test', project_name 'Project 1' and username 'test', 'Project 2'.")

# Insert 700 rows into the project_product table where project_id is 1 and product_id is a distinct random number between 1 and 1000
project_product_query = """
    INSERT INTO project_product (project_id, product_id)
    VALUES (%s, %s)
"""
project_ids = [1, 2]
project_product_counts = [700, 100]

for project_id, count in zip(project_ids, project_product_counts):
    product_ids = random.sample(range(1, 1001), count)
    for product_id in product_ids:
        cursor.execute(project_product_query, (project_id, product_id))
    connection.commit()
    print(f"Inserted {count} rows into the project_product table where project_id is {project_id} and product_id is a distinct random number between 1 and 1000.")


# Close the connection
cursor.close()
connection.close()
