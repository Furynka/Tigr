<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Tigr</title>
</head>
<body style="background-color: #404040;">
    <div class="container-fluid">
        <div class="row">
            <div style="text-align: center">
                <div>
                    <h1>Tigr</h1>
                    <h2>Welcome to Tigr, the food chain management system.</h2>
                    <h4>Sign in with your account.</h4>

                    <div class="sign-in">
                        <div class="icon-user"></div>
                        <form novalidate>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Enter Your Login" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="Enter Your Password" required>
                            </div>

                            <button type="submit" class="btn btn-primary">Sign in</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>