# Posture_Reminder


This is my first published app. It has 2 main features that are based upon sending a notification to the user as a reminder to have proper posture.

1) Sending a notification alert based on a schedule
This feature uses Android Alarm Manager to send a custom notification to the user based on the chosen time increment.

2) Sending a "smart alert"
This features attempts to guess when the user is using improper posture based on data from the accelerometer sensor listener. Approximately every minute, this accelerometer service is started to read the device angle relative to the ground. If the screen is on and the y-axis angle is between 0 and 35 degrees,  it is assumed that the user has his neck tilted downward in order to view the screen. In that case, a notification is sent.

More information available at or-oz.com/posture

