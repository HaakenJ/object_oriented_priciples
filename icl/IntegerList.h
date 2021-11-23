//
// Created by Haaken on 2/18/2021.
//

#ifndef ICL_INTEGERLIST_H
#define ICL_INTEGERLIST_H


class IntegerList {
public:
    IntegerList(int); // Constructor
    ~IntegerList(); // Destructor
    IntegerList(const IntegerList &); // Copy constructor
    IntegerList& operator=(const IntegerList &);
// Overloaded assignment operator
    void addElement(int); // Add element to list
    int findElement(int); // Find element; return index or -1
private:
    int *list;
    int size; // Size (or capacity) of list
    int numElements; // Number of elements in list
    void resize(); // Resize the list when full
};


#endif //ICL_INTEGERLIST_H
