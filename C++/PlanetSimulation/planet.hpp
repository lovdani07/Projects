#include "plant.hpp"
#include <fstream>
#include <sstream>
#include <vector>
#include <iostream>

using namespace std;

class Planet {
    private:
        fstream _x;
        string temp;
        int radiation;
        vector<Plant*> plants;

    public:
        int day;
        int strongestNutrition = -1;
        char strongestType = ' ';
        string strongestName = "";
        

        enum Exceptions{FILE_FAILURE, EMPTY_FILE};

        Planet(string fname){
            string name;
            char type;
            int nutrition;

            radiation = 0;
            _x.open(fname);
            if(_x.fail()){
                throw FILE_FAILURE;
            }
            if(_x.peek() == ifstream::traits_type::eof()){
                throw EMPTY_FILE;
            }
            
            cout << "Content of " << fname << ": " << endl;

            getline(_x,temp);
            istringstream line(temp);

            cout << temp << endl;

            for(int i = stoi(temp); i > 0; i--){
                getline(_x, temp);
                cout << temp << endl;
                line.clear();
                line.str(temp);
                line >> name >> type >> nutrition;

                if(type == 'p'){
                    plants.push_back(new Puffancs(name, type, nutrition));
                }
                else if(type == 'd'){
                    plants.push_back(new Deltafa(name, type, nutrition));
                }
                else{
                    plants.push_back(new Parabokor(name, type, nutrition));
                }
            }

            getline(_x,temp);
            cout << temp << endl;
            day = stoi(temp);
            _x.close();
        }

        void dayPasses(){
            int radTemp = 0;
            for(Plant* plant : plants){
                plant->afterRadiation(radiation);
                radTemp += plant->radiationNeeds();
            }
            radiation = radTemp;
        }

        void writeData(){
            cout << "Radiation: " << radiation;
            if(radiation > 3)
                cout << " -> Alpha" << endl;
            else if(radiation < -3)
                cout << " -> Delta" << endl;
            else
                cout << " -> No radiation" << endl;
            
            for(Plant* plant : plants){
                cout << plant->getName() << " " << plant->getType() << " " << plant->getNutrition() << endl;
            }
        }

        void strongestNow(){
            strongestNutrition = -1;
            for(Plant* actual : plants){
                if(actual->isAlive() && actual->getNutrition() > strongestNutrition){
                    strongestNutrition = actual->getNutrition();
                    strongestName = actual->getName();
                    strongestType = actual->getType();
                }
            }
        }
};