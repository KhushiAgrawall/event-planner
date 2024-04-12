# Event Planner

Event Planner is a simple Java-based application that helps users manage and organize events, venues, schedules, and attendees. This project provides a foundational codebase for learning and practicing TestNG, a popular Java testing framework, by adding and improving test cases as part of the learning process.

## Features

- Create and manage events with unique names, descriptions, and venues
- Add and remove attendees to events
- Manage venue information for events
- Create schedules for events with specific dates
- Find schedules for a specific date or date range
- Send invitations to event attendees via email (simulation)

## Project Structure

The project is organized into the following packages:

- `model`: Contains data classes such as `Attendee`, `Event`, `Schedule`, and `Venue`
- `service`: Contains service classes that perform various operations, such as `EventPlanner`, `ScheduleFinder`, and `InvitationSender`
- `main`: Contains the main class (`Main`) that runs the application

## Setup and Usage

### Prerequisites

- JDK 8 or later

### Build

To build the project, navigate to the project root directory and run the following command:

```bash
./gradlew clean build
```
## License
This project is licensed under the MIT License.