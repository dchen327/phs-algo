'''
Write a telephone lookup program, Telephone.java. Read the data set of 1,000 
names and telephone numbers from the attached file. It contains the numbers in 
random order. Handle lookups by name and also reverse lookups by phone number. 
Use Comparator and a binary search for both lookups.

Run this program with `python3 telephone.py`

Python 3.8
Author: David Chen
'''


class TelephoneSearch:

    def __init__(self):
        self.name_to_num = []
        self.num_to_name = []
        self.load_data()

    def load_data(self):
        """ Read in names and numbers from file """
        with open('sample.txt') as f:
            data = f.read().splitlines()
            # split each line into a (name, num) tuple
            data = [line.split(',') for line in data]
            # sorting by names
            self.name_to_num = sorted(data)
            # sorting by second value (numbers)
            self.num_to_name = [(num, name) for name,
                                num in sorted(data, key=lambda x: x[-1])]

    def binary_search(self, key, values):
        """ Look for given key in a values with binary search """
        low, high = 0, len(values) - 1
        while low <= high:
            mid = (low + high) // 2
            if values[mid][0] < key:
                low = mid + 1
            elif values[mid][0] > key:
                high = mid - 1
            else:
                # return the value in the key, value pair
                return values[mid][1]

        return "Item not found"  # if we get here, the key isn't present

    def search_by_name(self, name):
        """ Search by name """
        return self.binary_search(name, self.name_to_num)

    def search_by_num(self, num):
        """ Search by name """
        return self.binary_search(num, self.num_to_name)


if __name__ == '__main__':
    telephone_search = TelephoneSearch()
    for _ in range(3):
        name = input('Enter a name: ')
        print(name, telephone_search.search_by_name(name), sep=' | ')
    for _ in range(3):
        num = input('Enter a number: ')
        print(num, telephone_search.search_by_num(num), sep=' | ')


'''
The input/output:

Enter a name: Sojphczcji Bxau
Sojphczcji Bxau | 736-703-9350
Enter a name: Kcxvlqpqrx Fmcdzx
Kcxvlqpqrx Fmcdzx | 395-043-0182
Enter a name: a random name
a random name | Item not found
Enter a number: 512-461-4884
512-461-4884 | Fjtcgrqb Jmyxhiqky
Enter a number: 988-166-7149 
988-166-7149 | Pwzqploeq Oklbbtjexl
Enter a number: 000-000-0000
000-000-0000 | Item not found
'''
