#include <iostream>
#include "planet.hpp"

using namespace std;

#define NORMAL_MODE
#ifdef NORMAL_MODE

int main(){

    string fname;

    cout << "Give me the name of the file please: ";
    cin >> fname;
    try{
        Planet planet(fname);

        for(int i = 0; i < planet.day; i++){
            cout << "-----------------" << endl;
            cout << i+1 << ". nap" << endl;
            planet.dayPasses();
            planet.writeData();
        }
        
        cout << "-----------------" << endl;
        planet.strongestNow();
        if(planet.strongestNutrition == -1)
            cout << "Unfortunatelly the plants went extinct :(" << endl;
        else
            cout << "Strongest: " << planet.strongestName <<  " " << planet.strongestType << " " << planet.strongestNutrition << endl;

    }
    catch(Planet::Exceptions e)
    {
        if(e == Planet::FILE_FAILURE)
            cout << "Could not read the file: " << fname << endl;
        if(e == Planet::EMPTY_FILE)
            cout << "Empty file, cannot read that!" << endl;
    }
    
    return 0;
}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

TEST_CASE("Derived of 'Noveny'"){
    SECTION("Puffancs"){
        Puffancs test("Falank", 'p', 7);
        CHECK(test.isAlive());
        CHECK(test.radiationNeeds() == 10);
    }
    SECTION("Deltafa"){
        Deltafa test("Sudar", 'd', 5);
        CHECK(test.isAlive());
        CHECK(test.radiationNeeds() == -1);
    }
    SECTION("Parabokor"){
        Parabokor test("Kopcos", 'b', 4);
        CHECK(test.isAlive());
        CHECK(test.radiationNeeds() == 0);
    }
}

TEST_CASE("Planet class"){
    SECTION("Constructor"){
        CHECK_THROWS(Planet testPlaner("noFile.txt"));
        CHECK_NOTHROW(Planet testPlanet("minta1.txt"));
    }
    SECTION("Functions"){
        Planet testPlanet("minta1.txt");
        CHECK(testPlanet.day == 5);
        testPlanet.strongestNow();
        CHECK(testPlanet.strongestName == "Falank");
        CHECK(testPlanet.strongestNutrition == 7);
    }
    SECTION("Check for strongest after x days"){
        string strongest[] = {"Falank","Falank","Falank","Falank","Falank"};
        Planet testPlanet("minta1.txt");
        for(int i = 0; i < testPlanet.day; i++){
            testPlanet.dayPasses();
            testPlanet.strongestNow();
            CHECK(testPlanet.strongestName == strongest[i]);
        }
    }
}

TEST_CASE("Right strongest plant"){
    Planet test1("minta1.txt");
    Planet test2("minta2.txt");
    Planet test3("minta3.txt");
    Planet test4("minta4.txt");
    Planet test5("minta5.txt");

    for(int i = 0; i < 15; i++){
        test1.dayPasses();
        test2.dayPasses();
        test3.dayPasses();
        test4.dayPasses();
        test5.dayPasses();
        if(i == 4)
            test1.strongestNow();
        if(i == 14)
            test2.strongestNow();
        if(i == 3)
            test3.strongestNow();
        if(i == 7)
            test4.strongestNow();
        if(i == 9)
            test5.strongestNow();
    }
    

    CHECK(test1.strongestName == "Falank");
    CHECK(test2.strongestName == "");
    CHECK(test3.strongestName == "Falank");
    CHECK(test4.strongestName == "Kopcos");
    CHECK(test5.strongestName == "Kopcos");

}

#endif