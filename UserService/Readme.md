UserServices

This service has privilege to handle based on user. By default, is Guest profile is there.
User & Creator can log in or registered it. Admin and SuperAdmin has a separate page for login. 


Below cmd is used to build api
```` 
mvn clean install 
````
Profiles and their access
1. Guest - Default profile, can view public resources like courses, blogs, etc.
2. User - Can access user-specific resources, like create an account, see own profile, update and delete it.
3. Creator - Can see own profile,create, update and delete it.
4. Admin - Can see student & creator profile, can delete their profiles.
5. SuperAdmin - Has the highest level of access, including managing Admins. Like create student, creator, admin and update, delete their profiles.

