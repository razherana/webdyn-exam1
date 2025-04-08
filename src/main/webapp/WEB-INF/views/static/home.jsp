<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="models.*" %>

<%
    ArrayList<Prevision> previsions = (ArrayList<Prevision>) request.getAttribute("previsions");
    HashMap<Integer, Double> previsionDepenses = (HashMap<Integer, Double>) request.getAttribute("previsionDepenses");
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
                        <a class="nav-link active" href="."><i class="fas fa-home me-1"></i> Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/list.prevision"><i class="fa fa-chart-line me-1"></i> Prevision</a>
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
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="fas fa-file-invoice-dollar me-2"></i>Financial Transactions</h2>
        </div>
        
        <!-- Filter Form -->
        <div class="card filter-form mb-4" id="filterForm" style="display: none;">
            <div class="card-body">
                <form class="row g-3">
                    <div class="col-md-6">
                        <label for="searchInput" class="form-label"><i class="fas fa-search me-1"></i>Search</label>
                        <input type="text" class="form-control" id="searchInput" placeholder="Search by libelle...">
                    </div>
                    <div class="col-12 text-end">
                        <button type="submit" class="btn btn-dark"><i class="fas fa-search me-1"></i>Apply Filters</button>
                        <button type="reset" class="btn btn-outline-secondary ms-2"><i class="fas fa-redo me-1"></i>Reset</button>
                    </div>
                </form>
            </div>
        </div>
        
        <!-- Table Card -->
        <div class="card table-card">
            <div class="card-body p-0">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr>
                            <th scope="col"><i class="fas fa-tag me-1"></i>Libelle</th>
                            <th scope="col"><i class="fas fa-chart-line me-1"></i>Prevision</th>
                            <th scope="col"><i class="fas fa-money-bill-wave me-1"></i>Reste</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if(previsions != null && previsions.size() > 0) { 
                            for(Prevision prevision : previsions) { %>
                                <tr>
                                    <td><%= prevision.getLibelle() %></td>
                                    <td><%= prevision.getMontant() %> Ar</td>
                                    <td><%= prevision.getMontant() - previsionDepenses.getOrDefault(prevision.getId(), 0.0) %> Ar</td>
                                </tr>
                        <% }
                        } else { %>
                            <tr>
                                <td colspan="3" class="text-center"><b>Pas de donnees</b></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="<%= request.getContextPath() %>/static/assets/bs5.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>