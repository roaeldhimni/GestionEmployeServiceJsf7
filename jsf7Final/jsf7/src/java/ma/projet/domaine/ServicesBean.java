/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ma.projet.beans.Employe;
import ma.projet.beans.Services;
import ma.projet.service.EmployeService;
import ma.projet.service.ServicesService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author lenovo
 */
@ManagedBean(name = "serviceBean")
@ViewScoped

public class ServicesBean {
    
    private Employe employe;
    private Services service;
    private ServicesService serviceService;
    private List<Services> services;
    private List<Employe> employes;
    private EmployeService employeService;
    private ChartModel barModel;
     private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);

        List<Services> allServices = serviceService.getAll();
        for (Services service : allServices) {
            TreeNode serviceNode = new DefaultTreeNode("service", service, root);

            List<Employe> employes = service.getEmployes();
            for (Employe employe : employes) {
                new DefaultTreeNode("employee", employe, serviceNode);
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

     public ServicesBean() {
        service = new Services();
        serviceService = new ServicesService();
        employe = new Employe();
        employeService = new EmployeService();
          barModel = initBarModel(); 
    }
     
     public List<Services> getServices() {
        if (services == null) {
            services = serviceService.getAll();
        }
        return services;
    }
     
     public void setServices(List<Services> services) {
        this.services = services;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }
    
    public String onCreateAction() {
        serviceService.create(service);
        service  = new Services();
        return null;
    }
    public void onDeleteAction() {
        //service.setEmployes(null);
        serviceService.delete(service);

    }

    public ServicesService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServicesService serviceService) {
        this.serviceService = serviceService;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }
    
     public void onEdit(RowEditEvent event) {
        service = (Services) event.getObject();
        serviceService.update(service);
    }
    
     public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        //getEmployes();
    }

    public void onCancel(RowEditEvent event){}
      public ChartModel getBarModel() {
        return barModel;
    }

   
    public ChartModel initBarModel() {
    CartesianChartModel model = new CartesianChartModel();
    ChartSeries services = new ChartSeries();
    services.setLabel("services");
    model.setAnimate(true);

    List<Services> allServices = serviceService.getAll();
    for (Services m : allServices) {
        services.set(m.getNom(), m.getEmployes().size());
        System.out.println("Service: " + m.getNom() + ", Nombre d'employés: " + m.getEmployes().size());
    }

    model.addSeries(services);

    return model;
}}
