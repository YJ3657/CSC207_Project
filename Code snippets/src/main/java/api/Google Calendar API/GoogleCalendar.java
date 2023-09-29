import com.google.api.services.calendar.Calendar;

// ...

// Initialize Calendar service with valid OAuth credentials
Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
        .setApplicationName("applicationName").build();

// Retrieve the calendar
        com.google.api.services.calendar.model.Calendar calendar =
        service.calendars().get('primary').execute();

        System.out.println(calendar.getSummary());