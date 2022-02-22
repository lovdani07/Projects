#include <string>

using namespace std;

class Plant{
    protected:
        bool alive;
        char type;
        string name;
        int nutrition;
    
        Plant(string name, char type, int nutrition) : alive(true), name(name), type(type), nutrition(nutrition) {}

    public:
        string getName(){
            return name;
        }

        char getType(){
            return type;
        }

        bool isAlive(){
            return alive;
        }

        int getNutrition(){
            return nutrition;
        }

        virtual int radiationNeeds() = 0;
        virtual void afterRadiation(int radiation) = 0;
};

class Puffancs : public Plant{
    public:
        Puffancs(string name, char type, int nutrition) : Plant(name, type, nutrition){}
        

        int radiationNeeds() override {
            if(!alive)
                return 0;

            return 10;
        }

        void afterRadiation(int radiation) override {
            if(alive){
                if(radiation > 3){
                    nutrition += 2;
                }
                else if(radiation < -3){
                    nutrition += -2;
                }
                else{
                    nutrition += -1;
                }
                if(nutrition <= 0 || nutrition > 10)
                    alive = false;
            }
        }
};

class Deltafa : public Plant{
    public:
        Deltafa(string name, char type, int nutrition) : Plant(name, type, nutrition){}

        int radiationNeeds() override {
            if(!alive)
                return 0;

            if(nutrition < 5){
                return -4;
            }
            else if(nutrition < 11){
                return -1;
            }
            else{
                return 0;
            }
        }

        void afterRadiation(int radiation) override {
            if(alive){
                if(radiation > 3){
                    nutrition += -3;
                }
                else if(radiation < -3){
                    nutrition += 4;
                }
                else{
                    nutrition += -1;
                }
                if(nutrition <= 0)
                    alive = false;
            }
        }
};

class Parabokor : public Plant{
    public:
        Parabokor(string name, char type, int nutrition) : Plant(name, type, nutrition){}

        int radiationNeeds() override {
            return 0;
        }

        void afterRadiation(int radiation) override {
            if(alive){
                if(radiation > 3 || radiation < -3){
                    nutrition++;
                }
                else{
                    nutrition--;
                }
                if(nutrition <= 0)
                    alive = false;
            }
        }
};