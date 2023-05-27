This sample application shows how spring security works :) <br>
There are 4 resources
<br> 1 public resource : no authorization needed can be accessed  
<br> 2 Admin resource which can be only accessed by users having ADMIN authority
<br> 3 User resource can be accessed by USER as well as ADMIN
<br> Auth controller creates JWTs. If role= admin the creates JWT with authority ADMIN else USER

Ideally Auth controller will have a more complex logic like getting authority from LDAP etc but for this POC this is OK :)