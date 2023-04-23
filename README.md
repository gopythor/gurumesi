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
  [x]        ㄴ later it will be checked for review writer verification.

### Booking
- [x] Book a reservation
- [X] Check Bookings and change to expired when booking time is left less than 10 min.

### Kiosk
- [x] confirm a visit
- [x]  check approved bookings

### Owner
- [x] Register a restaurant
- [x] Inquire about reservations

#### Owner Additional Function
- [x] approve or reject a reservation

#### Upgrade
- [x] Upgrade Login function with jwt
- [x] Upgrade Error function 
