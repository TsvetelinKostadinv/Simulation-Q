# Simulation-Q

**General info about current state**

Travis CI: [![Build Status](https://travis-ci.org/TsvetelinKostadinv/Simulation-Q.svg?branch=master)](https://travis-ci.org/TsvetelinKostadinv/Simulation-Q)

## Goals
Simulation-Q is designed to model quantum phenomena in a stable package both for home and research use.
The simulation should be fast enough so it can compete with a real quantum computer.

## Contribution
Everyone eager enough and excited about this project is free to fork it and work or even contact me([here](tsvetelinkostadinovts@gmail.com ))


## Implementation details
It started off as a Java project solely because I am most familiar with it. However, it may be ported to a lower level lang one day...


## How to test it yourself
This section is likely to change a lot so I am putting it at the bottom.
As of [this commit](https://github.com/TsvetelinKostadinv/Simulation-Q/tree/a5455c2a274b49c0c6c0440cc4c245737e4f0782 "this commit") the project is yet to have a GUI( or a proper CLI for that matter ). Fear not, I am working on it... It will be done, eventually.
So, back on track.
First you have to clone the project, wither way you prefer, you need it on your local machine.
Next to run the project you just need to open command prompt in the main directory and type:
```
gradlew clean build run
```
or
```
./gradlew clean build run
```
on Unix based systems
This will construct a sample quantum register and apply sample transformations( nothing if I recall correctly ), and collapses it a million times and performs a poor microbenchmark saying how long it took.
However, if you want to tinker with it you can navigate to the file named Presenting.java
At the top you will find constants with explanatory comments so that you can change them up and experiment.
