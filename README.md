# medicine-reminder-telegram-bot
A Telegram-based medicine reminder and family adherence tracking system built with Java, Spring Boot, PostgreSQL, and Telegram Bot API.
# Medicine Reminder & Family Adherence Tracking Bot

## Project Overview

Medicine Reminder & Family Adherence Tracking Bot is a Telegram-based healthcare application designed to help individuals and families manage daily medication schedules, improve adherence, and reduce missed doses.

The project was initially created to help family members who need to take medicines regularly by providing automated reminders, adherence tracking, snooze functionality, and family monitoring capabilities through Telegram.

The application leverages Telegram's messaging platform to deliver reminders directly to users while maintaining a lightweight and cost-effective architecture.

Current development is focused on **Family Mode**, where family members can manage medicines, caregivers can receive reports, and a Family Administrator can monitor adherence trends and missed medicines.

Future versions may evolve into a public healthcare product with privacy-focused controls and role-based access management.

---

## Problem Statement

Many people forget to take medicines on time, especially when managing multiple medications throughout the day.

Common challenges include:

* Missing medicine schedules
* Lack of medication tracking
* No visibility for caregivers
* Difficulty monitoring adherence patterns
* No centralized reporting for families

This project aims to solve these problems through automated reminders, tracking, and reporting.

---

## Objectives

### Primary Objectives

* Send medicine reminders through Telegram
* Improve medication adherence
* Reduce missed medicines
* Track medicine intake activity
* Record delays and snooze events
* Generate adherence reports

### Secondary Objectives

* Support caregiver monitoring
* Provide family-level health tracking
* Generate weekly adherence summaries
* Detect inactive or blocked users

---

## Current Project Mode

### Family Mode (Version 1)

In Family Mode:

* Family members use the bot
* Patients manage their medicines
* Caregivers can receive reports
* Family Administrator receives monitoring reports
* Missed medicines are tracked and reported

### Future Public Mode

Planned future enhancements:

* Privacy-first architecture
* Role-based access control
* Consent management
* Multi-user scalability
* Public onboarding

---

## Key Features

### User Profile Management

* Create user profile
* Edit profile information
* Manage personal reminder preferences

### Medicine Management

* Add medicines
* Edit medicines
* Delete medicines
* View medicine list
* Prevent duplicate medicine entries

### Reminder Engine

* Scheduled medicine reminders
* Telegram push notifications
* Multiple reminder support
* Configurable reminder settings

### User Actions

* Mark medicine as Taken
* Snooze reminders
* Skip reminders

### Adherence Tracking

* Track taken medicines
* Track delayed medicines
* Track missed medicines
* Calculate adherence percentages

### Reporting

* Daily adherence reports
* Weekly adherence reports
* Family-level summaries

### Caregiver Monitoring

* Optional caregiver assignment
* Daily summaries
* Weekly summaries
* Missed medicine alerts

### Family Administrator Monitoring

* Family-wide adherence overview
* Missed medicine alerts
* Blocked bot alerts
* Administrative reporting

---

## Planned Commands

### Profile Commands

/start

/profile

/editprofile

### Medicine Commands

/addmedicine

/editmedicine

/deletemedicine

/medicines

### Report Commands

/today

/weeklyreport

/monthlyreport

### Caregiver Commands

/addcaregiver

/removecaregiver

### Administrator Commands

/familyreport

/missedtoday

/adherence

/userdetails

---

## High-Level Architecture

Telegram Users
|
Telegram Bot API
|
Spring Boot Application
|
+----------------------+
| Reminder Engine      |
| Reporting Engine     |
| Admin Module         |
| Caregiver Module     |
+----------------------+
|
PostgreSQL Database

---

## Technology Stack

### Backend

* Java 21
* Spring Boot 3

### Database

* PostgreSQL

### Messaging Platform

* Telegram Bot API

### Scheduling

* Spring Scheduler

### Build Tool

* Maven

### Version Control

* Git
* GitHub

### Deployment

* Oracle Cloud Free Tier

---

## Non-Functional Requirements

* Free to operate during initial stages
* Fast response times
* Persistent data storage
* Reliable reminder delivery
* Scalable architecture for future growth
* Maintainable and modular codebase

---

## Development Approach

This project follows a structured software engineering approach:

1. Requirements Gathering
2. Analysis
3. Solution Design
4. Database Design
5. Architecture Design
6. Development
7. Testing
8. Deployment
9. Monitoring
10. Continuous Improvement

---

## Project Roadmap

### Version 1.0

* User Profiles
* Medicine Management
* Reminder Engine
* Snooze Functionality
* Adherence Tracking
* Daily Reports
* Weekly Reports
* Caregiver Monitoring
* Family Administrator Monitoring

### Version 2.0

* Mobile Application
* Web Dashboard
* Report Export
* Enhanced Analytics

### Version 3.0

* Public Product Mode
* Privacy Controls
* Multi-Family Support
* Role-Based Access Control

---

## Project Status

Current Status:

Planning & Architecture Phase

Next Milestone:

Telegram Bot Setup and Spring Boot Project Initialization

---

## Author

Akashdeep Singh

Software Engineer | Java Developer

Building practical software solutions using Java, Spring Boot, PostgreSQL, Automation, and Cloud Technologies.
