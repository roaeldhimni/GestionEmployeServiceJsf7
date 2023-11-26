package ma.projet.domaine;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Services;
import ma.projet.service.EmployeService;
import ma.projet.service.ServicesService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "EmployeBean")
public class EmployeBean {
    private Employe Employe;
    private Services service;
    private List<Employe> Employes;
    private List<Employe> EmployesBetweenDates;
    private EmployeService EmployeService;
    private ServicesService serviceService;
    private List<Employe> chefs;
    private Employe selectedChef;
    private static ChartModel barModel;
    private Date date1;
    private Date date2;
    

    public EmployeBean() {
        Employe = new Employe();
            Employe.setService(new Services());
        EmployeService = new EmployeService();
        serviceService = new ServicesService();
        chefs = EmployeService.getAll();
        selectedChef = new Employe();
    }

    public List<Employe> getEmployes() {
        if (Employes == null) {
            Employes = EmployeService.getAll();
        }
        return Employes;
    }

    public void setEmployes(List<Employe> Employes) {
        this.Employes = Employes;
    }

    public EmployeService getEmployeService() {
        return EmployeService;
    }

    public void setEmployeService(EmployeService EmployeService) {
        this.EmployeService = EmployeService;
    }

    public Employe getEmploye() {
        return Employe;
    }

    public void setEmploye(Employe Employe) {
        this.Employe = Employe;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String onCreateAction() {
   EmployeService employeService = new EmployeService();

    // Set the selected chef to the employe
    Employe.setEmploye(selectedChef);

    // Create the employe
    employeService.create(Employe);

    // Reset employe and selectedChef
    Employe = new Employe();
    selectedChef = null;

    return null;
    }

  
        public String onDeleteAction() {
        Employe.setService(null);
        EmployeService.delete(Employe);
        return null;
    }


    public List<Employe> serviceLoad() {
        for (Employe e : EmployeService.getAll()) {
            if (e.getService().equals(service)) {
                Employes.add(e);
            }
        }
        return Employes;
    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        getEmployes();
    }
     public void onCancel(RowEditEvent event) {
    }

         public void onEdit(RowEditEvent event) {
        try  {
        Employe = (Employe) event.getObject();
        Services s = serviceService.getById(this.Employe.getService().getId());
    
        Employe.setService(s);
        Employe.getService().setNom(s.getNom());
        EmployeService.update(Employe);
    } catch (Exception e) {
       
            e.printStackTrace();
    }
        
    }





  
    public ChartModel getBarModel() {
        return barModel;
    }




    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public List<Employe> getEmployesBetweenDates() {
        return EmployesBetweenDates;
    }

    public void setEmployesBetweenDates(List<Employe> EmployesBetweenDates) {
        this.EmployesBetweenDates = EmployesBetweenDates;
    }
    public List<Employe> getChefs() {
    return chefs;
}

public Employe getSelectedChef() {
    return selectedChef;
}

public void setSelectedChef(Employe selectedChef) {
    this.selectedChef = selectedChef;
}

}
