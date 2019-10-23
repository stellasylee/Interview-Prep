import pandas as pd

raw_data = {'name': ['Willard Morris', 'Al Jennings', 'Omar Mullins', 'Spencer McDaniel'],
                'age': [20, 19, 22, 21],
                'favorite_color': ['blue', 'blue', 'yellow', "green"],
                'grade': [88, 92, 95, 70]}

df = pd.DataFrame(raw_data)
df.head()

'''
You need to assign the following letter grades based on final_grade_pct in a new column named "final_grade_letter":
>90 - A
81-90 - B
71-80 - C
< 70 - D
Write a function using Python to loop through the table and assign the appropriate letter grades to each student, adding a new column to the existing dataframe, df.
'''

def letter (grade):
    if grade > 90:
        return 'A'
    elif grade > 80:
        return 'B'
    elif grade > 70:
        return 'C'
    else:
        return 'D'

df['letter_grade'] = df['grade'].apply(lambda x: letter(x))

for index, r in df.iterrows():
    print(r['letter_grade'])