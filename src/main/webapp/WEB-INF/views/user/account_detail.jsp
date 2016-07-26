<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/left_column_account.jspf"%>
<div class="col-md-6 account-right wow fadeInRight">

    <div class = hint>
        <p> Enter the password to save the changes or delete profile </p>
    </div>

    <form class="login_form"  name="user" action="/user/editAccount" method="post" id="login_form">
        <div>
            <span>Login</span>
            <p id="login">${user.login}</p>
        </div>
        <div>
            <span>First Name:</span>
            <input type="text" name="firstName" id="firstName" pattern=".{1,15}"
                   title="input 1-15 characters" value="${user.firstName}" required/>
        </div>
        <div>
            <span>Last Name:</span>
            <input type="text" name="secondName" id="secondName" value="${user.secondName}"
                   pattern=".{1,20}" title="input 1-20 characters"  required/>
        </div>
        <div>
            <span>Birthday:</span>
            <input type="date" name="birthday" id="birthday" value="${user.birthday}"
                   min="1900-01-01" max="2010-01-01"
                   title="input birthday" required/>
        </div>
        <div>
            <span>E-mail:</span>
            <input type="email" name="email" id="email" value="${user.email}"
                   title="input valid email" required/>
        </div>
        <div>
            <span>Current password:</span>
            <input type="password" name="password" id="password" required/>
        </div>
        <div>
            <span>New password:</span>
            <input type="password" name="new_password" id="new_password"
                   pattern=".{3,45}" title="input 3-45 characters" />
        </div>
        <input type="submit" value="Edit profile">
    </form>
</div>


</div>
<div class="clearfix"> </div>
</div>
</div>
<%@include file="../jspf/footer.jspf"%>