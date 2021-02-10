'''
Write a telephone lookup program, Telephone.java. Read the data set of 1,000 
names and telephone numbers from the attached file. It contains the numbers in 
random order. Handle lookups by name and also reverse lookups by phone number. 
Use Comparator and a binary search for both lookups.

Python 3.8
Author: David Chen
'''


class TelephoneSearch:

    def __init__(self):
        self.name_to_num = {}
        self.num_to_name = {}
        self.load_data()
        print(list(self.name_to_num.keys())[:10])
        print(list(self.num_to_name.keys())[:10])

    def load_data(self):
        with open('sample.txt') as f:
            data = f.read().splitlines()
            # split each line into a (name, num) tuple
            data = [line.split(',') for line in data]
            # sorting by names
            self.name_to_num = {name: num for name, num in sorted(data)}
            # sorting by second value (numbers)
            self.num_to_name = {num: name for name,
                                num in sorted(data, key=lambda x: x[-1])}


if __name__ == '__main__':
    telephone_search = TelephoneSearch()
    print(telephone_search.search_by_name('Sojphczcji Bxau'))
    print(telephone_search.search_by_num('370-962-6321'))
