
import sqlite3

conn = sqlite3.connect('../../al2000.db')

c = conn.cursor()

sql_file = open('Al2000.sql')
sql_as_string = sql_file.read()
c.executescript(sql_as_string)
sql_file.close()
