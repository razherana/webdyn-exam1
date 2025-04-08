<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="models.*" %>

<%
    ArrayList<Prevision> previsions = (ArrayList<Prevision>) request.getAttribute("previsions");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finance</title>
    <!-- Bootstrap 5 CSS -->
    <link href="<%= request.getContextPath() %>/static/assets/bs5.3/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome Icons -->
    <link href="<%= request.getContextPath() %>/static/assets/fa/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #343a40 !important;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .table-card {
            border-radius: 10px;
            overflow: hidden;
        }
        .table thead {
            background-color: #343a40;
            color: white;
        }
        .filter-form {
            background-color: #f1f3f5;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
        }
        .btn-toggle-filter {
            border-radius: 50px;
            padding: 8px 20px;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="#"><i class="fas fa-chart-line me-2"></i>FinanceApp</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="."><i class="fas fa-home me-1"></i> Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="<%= request.getContextPath() %>/list.prevision"><i class="fa fa-chart-line me-1"></i> Prevision</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/list.depense"><i class="fa fa-money-bill-wave me-1"></i> Depense</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container">
    
        <% String error = request.getParameter("error");
            if (error != null) { %>
            <div
                class="alert alert-danger alert-dismissible fade show"
                role="alert"
            >
                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="alert"
                    aria-label="Close"
                ></button>
                <strong>Erreur</strong> <%= error %>
            </div>
        <% } %>

        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="fas fa-file-invoice-dollar me-2"></i>Financial Transactions</h2>
            <div class="d-flex align-items-center">
                <a href="<%= request.getContextPath() %>/list.prevision" class="btn btn-dark btn-toggle-filter">
                    <i class="fas fa-list me-2"></i> Liste des Previsions
                </a>
            </div>
        </div>
        
        <!-- Table Card -->
        <form method="post" class="col-md-9 mx-auto border rounded px-4 py-4">
            <h2>Creer une prevision</h2>
            <div class="mb-3">
                <label for="libelle" class="form-label">Libelle</label>
                <input
                    type="text"
                    class="form-control"
                    name="libelle"
                    id="libelle"
                    aria-describedby="libelleHelpId"
                    placeholder="Nourriture, ..."
                    required
                />
                <small id="libelleHelpId" class="form-text text-muted"
                    >Libelle de la prevision</small
                >
            </div>
            <div class="mb-3">
                <label for="montant" class="form-label">Montant</label>
                <input
                    type="number"
                    class="form-control"
                    name="montant"
                    id="montant"
                    aria-describedby="libelleHelpId"
                    placeholder="200000, ..."
                    required
                    step="0.01"
                />
                <small id="montantHelpId" class="form-text text-muted"
                    >Montant de la prevision</small
                >
            </div>
            <div>
                <button class="btn btn-outline-dark" type="submit">
                    Creer
                </button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="<%= request.getContextPath() %>/static/assets/bs5.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>