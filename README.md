# gurumesi

## Project subject 
Implementation of a store table reservation service Using SpringBoot
Goal : Build a commerce server that mediates between restaurant owners and customers.

## Project structure
![image](https://user-images.githubusercontent.com/94863168/230899776-2b5ff4d8-5a94-4e2a-a4f6-44ae13a4dc50.png)

## Tech Stack
Language : Java  
DBMS : MariaDB
Java Version : Java 11  
IDE : Intellij IDEA 2023.3.3 (Ultimate Edition)  
Test Program : Intellij .http  

## User Server
### Customer
- [x]  Join the membership

### Owner
- [x]  Join the membership

##  Restaurant Server
### Customer
- [x] Inquire about restaurants by sort(ASC NAME, ADDRESS, STAR)
- [x] Inquire about a restaurant detail
- [x] Review a restaurant
       ㄴ later it will be checked for review writer verification.

### Booking
- [ ] Book a reservation

### Kiosk
- [ ] confirm a visit

### Owner
- [x] Register a restaurant
- [ ] Inquire about reservations

#### Owner Additional Function
- [ ] approve or reject a reservation

#### Upgrade
- [ ] Upgrade Login function with jwt
