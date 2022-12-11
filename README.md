# HELP-ME
#### Video Demo:  https://youtu.be/3YbaJKwomOo
#### Description:
> The help-me project was inspired by a brazilian web site know as
> [ReclameAqui](https://www.reclameaqui.com.br/), the objective of reclameAqui
> is help people to expose problems that they have with companies
> and them are not resolving.  
> Like reclameAqui, the **help-me** idea is help people that live in condominiums
> and improve communication between residents and apartment manager. People that
> have a problem can expose the problem in **help-me**, only residents and 
> apartment manager will see all problems related.  
> This way apartment manager can see what are the major problems and solve them.

#### Problems Categories:
> The kind of problmes that people can report are these:
>* FINANCIAL - 
>Problems like, can´t pay  condominum bill because of problems app and more
>related to Finance.
>* LEISURE - 
>Problems like swingpool dirty, playground in disrepair and others 
related to leisure.
>* INFRASTRUCTURE - 
>Problems related to parking lot, building painting, etc.
>* SECURITY -
>Problems related to access to the condominum, robberies, etc.
>* COMMUNICATION - 
> Problems related to warnings in the condominum that have many people that don't receive.

#### Design choices:
>* Choosed the mvc model with backend and frontend together in the same project because
> how the project is relatively small I can test and do deliveries more quickly than 
> separate frontend and backend in diferents repositories like microservices.
>* For authentication and security I'm using Spring security because this framework contains
>multiple functions that help in authentication and authorizations.
>* Folders are separated in ddd(domain driven design) , I choosed ddd because of the challenge
>I don't have persolnal projects with ddd and studied it , so I tried to learn more about ddd
>* The html files uses thymeleaf that is a Java template engine , it is integrated with Spring Framework
>so become helpfull.

#### Domains
>* Configuration - Contain only one class to do the Spring configuration
>* Improvement - The domain responsible for new improvements and changes 
>and count likes of improvements.
>* User - Responsible by user data changes like email,password.
>* UserAuthentication - Responsible by the authentication and authorization
> of user.
> 
> 
> 
#### Folders

>* Controller - Contain classes in this folder are responsible by the endPoints of the backend
>* DTO - Contain classes with the unique objectie of data traffic in align with Data transfer object pattern
>* Model - Contain Java Objects that represent the business
>* Repository - Contain java interfaces responsible by connect with database and queries
>* Service - Contain classes that do business logic and integrate with repository
>* Util - Contain classes that have functions usefull for more than one class.
>* Security - Contain Spring Security configuration.
>* Templates - Contain static files, to work with thymeleaf is necessary put the static files in 
>the folder with this name.

#### Where is running

>* I choosed heroku plataform to do the deploy because heroku is free and I wanted to learn more about it.
>* This is the url to access the project -> https://helpme-core.herokuapp.com/login

#### Observations
>* queries.sql file contain queries used to do tests.
>* IndexController.java is out of other folders because I didn´t find a model for it.
