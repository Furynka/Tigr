<!-- Created by Eva Ambrusova -->
<head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Tigr</title>
</head>
<body style="background-color: #404040;">
<div class="container-fluid">
<header class="navigation hide-for-small-only large">
        <div class="page-width site-navigation">
                <ul class="static-links">
                        <li><a href="#">Why to use Tigr?</a></li>
                        <li><a href="#">Contact</a></li>
                        <li class="right"><a href="#">Log out</a></li>
                </ul>
        </div>
        <nav>
                <div class="nav-item logo-container"><a href="#">Home</a></div>
                <ul class="nav-item menu-items inline-list">
                        <li><a href="#">Animal</a></li>
                        <li><a href="#">Environment</a></li>
                        <li><a href="#">Species</a></li>
                        <!--TODO: Users are visible only for admin-->
                        <li><a href="#">Users</a></li>
                        <li><a href="#">About Tigr</a></li>
                </ul>
        </nav>
</header>
<div class="row">
        <div>
                <div class="content">
                        <section class="environment-section page-width">
                                <h2 class="section-header">Manage your environments</h2>
                                <p class="section-header">In this page, you can add, delete or edit all food chain environments. Use the search bar for easier work!</p>

                                <div class="environment-section entity-list-panel">
                                        <section class="panel">
                                                <h1 class="component-header">
                                                        <span class="underline">Environment</span>
                                                </h1>
                                                <div class="search-add">
                                                        <span><input type="search" placeholder="search"></span>
                                                        <button class="btn btn-primary">GO!</button>
                                                        <button class="add btn btn-primary">+ New environment</button>
                                                </div>
                                                <div>
                                                        <ul class="link-strips">
                                                                <li class="list-item" title="Environment1 TODO">Environment1 TODO
                                                                        <span><button type="delete" class="btn btn-primary">Delete</button></span>
                                                                </li>
                                                                <li class="list-item" title="Environment2 TODO">Environment2 TODO
                                                                        <span><button type="delete" class="btn btn-primary">Delete</button></span>
                                                                </li>
                                                                <li class="list-item" title="Environment3 TODO">Environment3 TODO
                                                                        <span><button type="delete" class="btn btn-primary">Delete</button></span>
                                                                </li>
                                                        </ul>
                                                </div>
                                                <div class="center">
                                                        <button class="btn btn-primary" href="#">See all environments</button>
                                                </div>
                                        </section>
                                </div>
                        </section>
                </div>
        </div>
</div>
</div>
</body>