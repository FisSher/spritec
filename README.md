# Guide:
1. Using command line? Use gradlew cucumber, it will run the tests. Play with tags in build.gradle.


2. From intellij you can execute the Runner class

Troubleshot here: change intellij settings to build and run (and test too) using intellij instead of gradle.

#Approach and why:

I chose this scenarios because they are the most common in any API.

Login, do a GET or a POST. The logic inside is what really matters.
You have to make the most of reusable code.

I also did use some tagging and tested json schemas (they are powerful).

I wanted to try some things (luckily they did work) like the body from file, that's why 
you may find variations in the tests. There is no bearer token or things like that,
the api just responds 200 to almost everything, that is why I wanted to validate a schema.

About the UI tests, it has been a while since I used selenium, I don't know if devqa forms are intentionally
broken, but I found issues trying to do simple cases. There is a pretty full form opportunity but it works really bad.

There are 3 simple UI tests, correct, incorrect and special characters. But only E2E positive cases must be done here.

#Reports

There is a lovely report on the "reports" folder. No link to calliope, it is just
a paid tool and prefer to do a classic open source approach.

I made github actions publish the classic one.

#Next steps from here:
You didn't ask to do json schema validations, but I love them!
I would like to do some other endpoints, but they are more of the same.

Maybe create a better args mapper.

I would love to implement a prettier report like extent, allure, etc.

Investigate why github doesn't like my readme
