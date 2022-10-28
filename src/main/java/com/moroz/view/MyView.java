package com.moroz.view;

import com.moroz.controller.*;
import com.moroz.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {

    @Autowired
    private CityController cityController;
    @Autowired
    private ClinicController clinicController;
    @Autowired
    private DoctorController doctorController;
    @Autowired
    private ClientController clientController;
    @Autowired
    private RegionController regionController;
    @Autowired
    private ServiceController serviceController;
    @Autowired
    private PatientController patientController;
    @Autowired
    private DiagnosisController diagnosisController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final City nullCity = new City(null, null);
    private final Clinic nullClinic = new Clinic(null, null, null, null, null);
    private final Doctor nullDoctor = new Doctor(null, null, null, null);
    private final Client nullClient = new Client(null, null, null, null);
    private final Region nullRegion = new Region(null);
    private final Service nullService = new Service(null, null, null, null, null);
    private final Patient nullPatient = new Patient(null, null, null, null);
    private final Diagnosis nullDiagnosis = new Diagnosis(null, null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Region");
        menu.put("11", "  11 - Create Region");
        menu.put("12", "  12 - Update Region");
        menu.put("13", "  13 - Delete from Region");

        menu.put("2", "   2 - Table: City");
        menu.put("21", "  21 - Create City");
        menu.put("22", "  22 - Update City");
        menu.put("23", "  23 - Delete from City");
        menu.put("24", "  24 - Find all Cities");
        menu.put("25", "  25 - Find City by ID");

        menu.put("3", "   3 - Table: Service");
        menu.put("31", "  31 - Create Service");
        menu.put("32", "  32 - Update Service");
        menu.put("33", "  33 - Delete from Service");
        menu.put("34", "  34 - Find all Services");
        menu.put("35", "  35 - Find Service by ID");


        menu.put("4", "   4 - Table: Clinic");
        menu.put("41", "  41 - Create Clinic");
        menu.put("42", "  42 - Update Clinic");
        menu.put("43", "  43 - Delete from Clinic");
        menu.put("44", "  44 - Find all Clinics");
        menu.put("45", "  45 - Find Clinic by ID");

        menu.put("5", "   5 - Table: Doctor");
        menu.put("51", "  51 - Create Doctor");
        menu.put("52", "  52 - Update Doctor");
        menu.put("53", "  53 - Delete from Doctor");
        menu.put("54", "  54 - Find all Doctors");
        menu.put("55", "  55 - Find Doctor by ID");

        menu.put("6", "   6 - Table: Client");
        menu.put("61", "  61 - Create Client");
        menu.put("62", "  62 - Update Client");
        menu.put("63", "  63 - Delete from Client");
        menu.put("64", "  64 - Find all Clients");
        menu.put("65", "  65 - Find Client by ID");

        menu.put("7", "   7 - Table: Patient");
        menu.put("71", "  71 - Create Patient");
        menu.put("72", "  72 - Update Patient");
        menu.put("73", "  73 - Delete from Patient");
        menu.put("74", "  74 - Find all Patients");
        menu.put("75", "  75 - Find Patient by ID");

        menu.put("8", "   8 - Table: Diagnosis");
        menu.put("81", "  81 - Create Diagnosis");
        menu.put("82", "  82 - Update Diagnosis");
        menu.put("83", "  83 - Delete from Diagnosis");
        menu.put("84", "  84 - Find all Diagnoses");
        menu.put("85", "  85 - Find Diagnosis by ID");


        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createRegion);
        methodsMenu.put("12", this::updateRegion);
        methodsMenu.put("13", this::deleteFromRegion);
        methodsMenu.put("14", this::findAllRegions);
        methodsMenu.put("15", this::findRegionById);

        methodsMenu.put("21", this::createCity);
        methodsMenu.put("22", this::updateCity);
        methodsMenu.put("23", this::deleteFromCity);
        methodsMenu.put("24", this::findAllCities);
        methodsMenu.put("25", this::findCityById);

        methodsMenu.put("31", this::createService);
        methodsMenu.put("32", this::updateService);
        methodsMenu.put("33", this::deleteFromService);
        methodsMenu.put("34", this::findAllServices);
        methodsMenu.put("35", this::findServiceById);

        methodsMenu.put("41", this::createClinic);
        methodsMenu.put("42", this::updateClinic);
        methodsMenu.put("43", this::deleteFromClinic);
        methodsMenu.put("44", this::findAllClinics);
        methodsMenu.put("45", this::findClinicById);

        methodsMenu.put("51", this::createDoctor);
        methodsMenu.put("52", this::updateDoctor);
        methodsMenu.put("53", this::deleteFromDoctor);
        methodsMenu.put("54", this::findAllDoctors);
        methodsMenu.put("55", this::findDoctorById);

        methodsMenu.put("61", this::createClient);
        methodsMenu.put("62", this::updateClient);
        methodsMenu.put("63", this::deleteFromClient);
        methodsMenu.put("64", this::findAllClients);
        methodsMenu.put("65", this::findClientById);

        methodsMenu.put("71", this::createPatient);
        methodsMenu.put("72", this::updatePatient);
        methodsMenu.put("73", this::deleteFromPatient);
        methodsMenu.put("74", this::findAllPatients);
        methodsMenu.put("75", this::findPatientById);

        methodsMenu.put("81", this::createDiagnosis);
        methodsMenu.put("82", this::updateDiagnosis);
        methodsMenu.put("83", this::deleteFromDiagnosis);
        methodsMenu.put("84", this::findAllDiagnosis);
        methodsMenu.put("85", this::findDiagnosisById);


    }

    private void selectAllTable() {
        findAllRegions();
        findAllCities();
        findAllClinics();
        findAllDoctors();
        findAllClients();
        findAllServices();
        findAllPatients();
    }


    //endregion
    // region CITY ---------------------------------------------------
    private void createCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();

        City city = new City(cityName, regionName);

        int count = cityController.create(city);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        System.out.println("Input new 'city_name': ");
        String newCityName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String newRegionName = input.nextLine();

        City city = new City(newCityName, newRegionName);

        int count = cityController.update(cityName, city);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        int count = cityController.delete(cityName);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCities() {
        System.out.println("\nTable: CITY");
        List<City> cities = cityController.findAll();
        for (City city : cities) {
            System.out.println(city);
        }
    }

    private void findCityById() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        Optional<City> city = cityController.findById(cityName);
        System.out.println(city.orElse(nullCity));
    }

    //endregion
    // region DOCTOR ---------------------------------------------------
    private void createDoctor() {
        System.out.println("Input 'doctor_id': ");
        Integer doctorId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'doctor_name': ");
        String doctorName = input.nextLine();
        System.out.println("Input 'doctor_surname': ");
        String doctorSurname = input.nextLine();
        System.out.println("Input 'doctor_schedule': ");
        String doctorSchedule = input.nextLine();

        Doctor doctor = new Doctor(doctorId, doctorName, doctorSurname, doctorSchedule);

        int count = doctorController.create(doctor);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateDoctor() {
        System.out.println("Input 'doctor_id': ");
        Integer doctorId = Integer.valueOf(input.nextLine());

        System.out.println("Input 'new_doctor_name': ");
        String newDoctorName = input.nextLine();
        System.out.println("Input 'new_doctor_surname': ");
        String newDoctorSurname = input.nextLine();
        System.out.println("Input 'new_doctor_schedule': ");
        String newDoctorSchedule = input.nextLine();


        Doctor doctor = new Doctor(null, newDoctorName, newDoctorSurname, newDoctorSchedule);

        int count = doctorController.update(doctorId, doctor);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromDoctor() {
        System.out.println("Input 'doctor_id': ");
        Integer doctorId = Integer.valueOf(input.nextLine());

        int count = doctorController.delete(doctorId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllDoctors() {
        System.out.println("\nTable: DOCTOR");
        List<Doctor> doctors = doctorController.findAll();
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    private void findDoctorById() {
        System.out.println("Input 'doctor_id': ");
        Integer doctorId = Integer.valueOf(input.nextLine());

        Optional<Doctor> doctor = doctorController.findById(doctorId);
        System.out.println(doctor.orElse(nullDoctor));
    }


    //endregion
    // region CLINIC ---------------------------------------------------
    private void createClinic() {
        System.out.println("Input 'clinic_id': ");
        Integer clinicId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'clinic_name': ");
        String clinicName = input.nextLine();
        System.out.println("Input 'clinic_phone': ");
        String clinicPhone = input.nextLine();
        System.out.println("Input 'street_address': ");
        String streetAddress = input.nextLine();
        System.out.println("Input 'city': ");
        String city = input.nextLine();

        Clinic clinic = new Clinic(clinicId, clinicName, clinicPhone, streetAddress, city);

        int count = clinicController.create(clinic);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateRegion() {
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();

        System.out.println("Input new 'region_name': ");
        String newRegionName = input.nextLine();

        Region region = new Region(newRegionName);

        int count = regionController.update(regionName, region);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromRegion() {
        System.out.println("Input 'Region_name': ");
        String regionName = input.nextLine();

        int count = regionController.delete(regionName);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllRegions() {
        System.out.println("\nTable: REGION");
        List<Region> regions = regionController.findAll();
        for (Region region : regions) {
            System.out.println(region);
        }
    }

    private void findRegionById() {
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();

        Optional<Region> region = regionController.findById(regionName);
        System.out.println(region.orElse(nullRegion));
    }

    //endregion
    // region CLINIC ---------------------------------------------------
    private void createRegion() {
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();


        Region region = new Region(regionName);

        int count = regionController.create(region);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateClinic() {
        System.out.println("Input 'clinic_id': ");
        Integer clinicId = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'clinic_name': ");
        String newClinicName = input.nextLine();

        Clinic clinic = new Clinic(clinicId, newClinicName, "", "", "");

        int count = clinicController.update(clinicId, clinic);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromClinic() {
        System.out.println("Input 'clinicId': ");
        Integer clinicId = Integer.valueOf(input.nextLine());

        int count = clinicController.delete(clinicId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllClinics() {
        System.out.println("\nTable: CLINIC");
        List<Clinic> clinics = clinicController.findAll();
        for (Clinic clinic : clinics) {
            System.out.println(clinic);
        }
    }

    private void findClinicById() {
        System.out.println("Input 'clinic_id': ");
        Integer clinicId = Integer.valueOf(input.nextLine());

        Optional<Clinic> clinic = clinicController.findById(clinicId);
        System.out.println(clinic.orElse(nullClinic));
    }

    //endregion
    // region Service ---------------------------------------------------
    private void createService() {
        System.out.println("Input 'service_id': ");
        Integer serviceId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'service_name': ");
        String serviceName = input.nextLine();
        System.out.println("Input 'duration': ");
        String duration = input.nextLine();
        System.out.println("Input 'isAvailable': ");
        String isAvailable = input.nextLine();
        System.out.println("Input 'doctor_id': ");
        Integer doctorId = Integer.valueOf(input.nextLine());

        Service service = new Service(serviceId, serviceName, duration, isAvailable, doctorId);

        int count = serviceController.create(service);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateService() {
        System.out.println("Input 'service_id': ");
        Integer serviceId = Integer.valueOf(input.nextLine());

        System.out.println("Input 'new_service_name': ");
        String newServiceName = input.nextLine();
        System.out.println("Input 'new_service_duration': ");
        String newServiceDuration = input.nextLine();
        System.out.println("Input 'new_isAvailable': ");
        String isAvailable = input.nextLine();
        System.out.println("Input 'doctor_id': ");
        Integer doctorId = Integer.valueOf(input.nextLine());


        Service service = new Service(null, newServiceName, newServiceDuration, isAvailable, doctorId);

        int count = serviceController.update(serviceId, service);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromService() {
        System.out.println("Input 'service_id': ");
        Integer serviceId = Integer.valueOf(input.nextLine());

        int count = serviceController.delete(serviceId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllServices() {
        System.out.println("\nTable: SERVICE");
        List<Service> services = serviceController.findAll();
        for (Service service : services) {
            System.out.println(service);
        }
    }

    private void findServiceById() {
        System.out.println("Input 'service_id': ");
        Integer serviceId = Integer.valueOf(input.nextLine());

        Optional<Service> service = serviceController.findById(serviceId);
        System.out.println(service.orElse(nullService));
    }


    //endregion
    // region PATIENT ---------------------------------------------------
    private void createPatient() {
        System.out.println("Input 'patient_id': ");
        Integer patientId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'patient_breed': ");
        String patientBreed = input.nextLine();
        System.out.println("Input 'health_complains': ");
        String healthComplains = input.nextLine();
        System.out.println("Input 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());

        Patient patient = new Patient(patientId, patientBreed, healthComplains, clientId);

        int count = patientController.create(patient);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updatePatient() {
        System.out.println("Input 'patient_id': ");
        Integer patientId = Integer.valueOf(input.nextLine());

        System.out.println("Input 'new_patient_breed': ");
        String newPatientBreed = input.nextLine();
        System.out.println("Input 'new_patient_health_complains': ");
        String newHealthComplains = input.nextLine();
        System.out.println("Input 'new_client_id': ");
        Integer newClientId = Integer.valueOf(input.nextLine());

        Patient patient = new Patient(null, newPatientBreed, newHealthComplains, newClientId);

        int count = patientController.update(patientId, patient);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromPatient() {
        System.out.println("Input 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());

        int count = clientController.delete(clientId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllPatients() {
        System.out.println("\nTable: PATIENT");
        List<Patient> patients = patientController.findAll();
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private void findPatientById() {
        System.out.println("Input 'patient_id': ");
        Integer patientId = Integer.valueOf(input.nextLine());

        Optional<Patient> patient = patientController.findById(patientId);
        System.out.println(patient.orElse(nullPatient));
    }

    //endregion
    // region CLIENT ---------------------------------------------------
    private void createClient() {
        System.out.println("Input 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'client_name': ");
        String clientName = input.nextLine();
        System.out.println("Input 'client_surname': ");
        String clientSurname = input.nextLine();
        System.out.println("Input 'client_contact_number': ");
        String contactNumber = input.nextLine();

        Client client = new Client(clientId, clientName, clientSurname, contactNumber);

        int count = clientController.create(client);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateClient() {
        System.out.println("Input 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());

        System.out.println("Input 'new_client_name': ");
        String newClientName = input.nextLine();
        System.out.println("Input 'new_client_surname': ");
        String newClientSurname = input.nextLine();
        System.out.println("Input 'new_client_contact_number': ");
        String newContactNumber = input.nextLine();

        Client client = new Client(null, newClientName, newClientSurname, newContactNumber);

        int count = clientController.update(clientId, client);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromClient() {
        System.out.println("Input 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());

        int count = clientController.delete(clientId);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllClients() {
        System.out.println("\nTable: CLIENT");
        List<Client> clients = clientController.findAll();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void findClientById() {
        System.out.println("Input 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());

        Optional<Client> client = clientController.findById(clientId);
        System.out.println(client.orElse(nullClient));
    }

    //endregion
//endregion
    // region Diagnosis ---------------------------------------------------
    private void createDiagnosis() {
        System.out.println("Input 'diagnosis_name': ");
        String diagnosisName = input.nextLine();
        System.out.println("Input 'description': ");
        String description = input.nextLine();

        Diagnosis diagnosis = new Diagnosis(diagnosisName, description);

        int count = diagnosisController.create(diagnosis);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateDiagnosis() {
        System.out.println("Input 'diagnosis_name': ");
        String diagnosisName = input.nextLine();

        System.out.println("Input new 'description': ");
        String description = input.nextLine();

        Diagnosis diagnosis = new Diagnosis(diagnosisName, description);

        int count = diagnosisController.update(diagnosisName, diagnosis);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromDiagnosis() {
        System.out.println("Input 'diagnosis_name': ");
        String diagnosisName = input.nextLine();

        int count = diagnosisController.delete(diagnosisName);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllDiagnosis() {
        System.out.println("\nTable: DIAGNOSIS");
        List<Diagnosis> diagnoses = diagnosisController.findAll();
        for (Diagnosis diagnosis : diagnoses) {
            System.out.println(diagnosis);
        }
    }

    private void findDiagnosisById() {
        System.out.println("Input 'diagnosis_name': ");
        String diagnosisName = input.nextLine();

        Optional<Diagnosis> diagnosis = diagnosisController.findById(diagnosisName);
        System.out.println(diagnosis.orElse(nullDiagnosis));
    }

    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }
}

    //endregion



