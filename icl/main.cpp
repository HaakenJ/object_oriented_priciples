#include <iostream>

using namespace std;

// CarInfo struct definition
struct CarInfo {
    string make;
    string model;
    string color;
    int year;
};

// Specification file for the Sale class
class Sale {
public:
    Sale(double itemCost, double taxRate = 0.05) {
        this->itemCost = itemCost;
        this->taxRate = taxRate;
    }; // params: item cost, tax rate
    void setItemCost(double newCost) {
        this->itemCost = newCost;
    };
    void setTaxRate(double newRate) {
        this->taxRate = newRate;
    };
    double getItemCost() const {
        return itemCost;
    };
    double getTaxRate() const {
        return taxRate;
    };
    double getTax() const;
    double getTotal() const;
private:
    double itemCost; // cost of the item
    double taxRate; // sales tax rate
};

int main() {
    CarInfo* pCarInfo = new CarInfo;

    pCarInfo->color = "Tan";
    pCarInfo->make = "Toyota";
    pCarInfo->model = "4Runner";
    pCarInfo->year = 1990;

    cout << pCarInfo->color << endl;
    cout << pCarInfo->make << endl;
    cout << pCarInfo->model << endl;
    cout << pCarInfo->year << endl;

    delete pCarInfo;

    return 0;
}
