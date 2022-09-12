import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

import org.hibernate.HibernateException;


import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.TrainerDto;
import com.ideas2it.dto.TraineeDto;
import com.ideas2it.service.IEmployeeService;
import com.ideas2it.service.impl.EmployeeServiceImpl;
import com.ideas2it.util.Utility;
import com.ideas2it.util.Validation;
import com.ideas2it.exception.EmptyListException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;


public class EmployeeController {

    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(EmployeeController.class);

    private IEmployeeService<TrainerDto> trainerDtoEmployeeService = new EmployeeServiceImpl(new TrainerDto());
    private IEmployeeService<TraineeDto> traineeDtoEmployeeService = new EmployeeServiceImpl(new TraineeDto());


    public static void main(String[] args) {

        String log4jPath = "C:\\HIBERNATECOMPLETEregexpRojeCT\\log\\log4j.properties";
        PropertyConfigurator.configure(log4jPath);

        EmployeeController controller = new EmployeeController();
        System.out.println("Welcome to Ideas2IT Employee management portal!!\n");
        controller.init();
    }

    /**
     * used to interact with user
     *
     * @return {@link void}
     */
    public void init() {
        boolean isContinue = true;
        while (isContinue) {
            try {
                logger.info("Enter 1 to add Employee");
                logger.info("Enter 2 to display Employe ");
                logger.info("Enter 3 to asociate Employee");
                logger.info("Enter 4 to display Trainers and Trainees");
                logger.info("Press 5 to update Employee Phone Number");
                logger.info("Press 6 to delete Employee records");
                logger.info("Enter any other to exit");
                int userChoice = scanner.nextInt();
                switch (userChoice) {

                    case 1:
                        createEmployee();
                        break;

                    case 2:
                        logger.info("Press 1 to display trainer details");
                        logger.info("Press 2 to display trainee details");
                        logger.info("Press 3 to dispaly Trainer or Trainee by entering their EmployeId");
                        int usersChoice = scanner.nextInt();
                        if (usersChoice == 1) {
                            try {
                                displayAllTrainers();
                            } catch (EmptyListException e) {
                                logger.info(e.getMessage());
                            }
                        } else if (usersChoice == 2) {
                            displayAllTrainees();
                        } else if (usersChoice == 3) {
                            displayEmployeById();
                        }
                        break;

                    case 3:
                        associateTrainersAndTrainees();
                        break;

                    case 4:
                        displayTrainersAndTrainees();
                        break;

                    case 5:
                        updateEmployeePhoneNumber();
                        break;

                    case 6:
                        deleteEmployeeById();
                        break;

                    default:
                        isContinue = false;

                }
            } catch (InputMismatchException ex) {
                scanner.next();
                logger.info("Invalid Input please Enter again");
            }
        }
    }

    /**
     * used to collect and set employee details
     *
     * @return {@link void}
     */
    public void createEmployee() throws InputMismatchException {
        String employeeFirstName, employeeLastName;
        LocalDate dateOfBirth, dateOfJoin;
        long employeePhoneNumber;
        logger.info("Press 1 to add Trainer details");
        logger.info("Press 2 to add Trainees details");
        int userChoice = scanner.nextInt();
        if (userChoice == 1 || userChoice == 2) {
            logger.info("Enter Number of detail to add");
            int employeesToAdd = scanner.nextInt();
            for (int i = 0; i < employeesToAdd; i++) {
                employeeFirstName = getFirstName();
                employeeLastName = getLastName();
                dateOfBirth = getDateOfBirth();
                dateOfJoin = getDateOfJoin();
                employeePhoneNumber = getPhoneNumber();
                int yearOfJoin = dateOfJoin.getYear();
                String employeeId = Utility.generateEmployeeId(yearOfJoin);
                String emailId = Utility.generateMailId(employeeFirstName, employeeLastName);
                logger.info("Your employee id is " + employeeId + "\n" + " Email id is" + emailId);
                logger.info("Kindly take a note of it");

                if (userChoice == 1) {

                    TrainerDto trainerDto = new TrainerDto(employeeId, employeeFirstName, employeeLastName, employeePhoneNumber, dateOfBirth, dateOfJoin, emailId);
                    trainerDtoEmployeeService.addEmployee(trainerDto);

                } else if (userChoice == 2) {

                    TraineeDto traineeDto = new TraineeDto(employeeId, employeeFirstName, employeeLastName, employeePhoneNumber, dateOfBirth, dateOfJoin, emailId);
                    traineeDtoEmployeeService.addEmployee(traineeDto);
                }
            }
        } else {
            logger.info("Invalid input ");
        }
    }


    /**
     * used to collect and return employeeFirstName
     *
     * @return {@link String} employeeFirstName
     */
    public String getFirstName() {
        logger.info("Enter First Name");
        String employeeFirstName = scanner.next();
        return employeeFirstName;
    }


    /**
     * used to collect and return employeeLastName
     *
     * @return {@link String} employeeLastName
     */
    public String getLastName() {
        logger.info("Enter Last Name");
        String employeeLastName = scanner.next();
        return employeeLastName;
    }


    /**
     * used to collect and return employeePhoneNumber
     *
     * @return {@link long} employeePhoneNumber
     */
    public long getPhoneNumber() {
        boolean sflag = true;
        String phoneNumber = null;
        while (sflag) {
            logger.info("Enter PhoneNumber");
            phoneNumber = scanner.next();
            if (Validation.validatePhoneNumber(phoneNumber)) {
                sflag = false;
                break;
            } else {
                logger.info("Invalid");
            }
        }
        long employeePhoneNumber = Long.parseLong(phoneNumber);
        return employeePhoneNumber;
    }

    /**
     * used to collect and return employeeDateOfBirth
     *
     * @return {@link LocalDate} employeeDateOfBirth
     */
    public LocalDate getDateOfBirth() {
        boolean flag = true;
        LocalDate employeeDateOfBirth = null;
        while (flag) {
            logger.info("Enter date of Birth in dd-mm-yyyy or yyyy-mm-dd ");
            String dateOfBirth = scanner.next();
            try {
                employeeDateOfBirth = LocalDate.parse(Validation.validateDate(dateOfBirth));
                if (Validation.getAge(employeeDateOfBirth) < 20) {
                    ;
                    logger.info("You cannot proceed here becoz you are underage");
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            } catch (DateTimeParseException exception) {
                logger.info("Invalid");
            }
        }
        return employeeDateOfBirth;
    }

    /**
     * used to collect and return employeeDateOfJoin
     *
     * @return {@link LocalDate} employeeDateOfJoin
     */
    public LocalDate getDateOfJoin() {
        boolean flag = true;
        LocalDate employeeDateOfJoin = null;
        while (flag) {
            logger.info("Enter date of join in dd-mm-yyyy or yyyy-mm-dd ");
            String dateOfJoin = scanner.next();
            try {
                employeeDateOfJoin = LocalDate.parse(Validation.validateDate(dateOfJoin));
                if (Validation.getExperience(employeeDateOfJoin) < 0 || Validation.getExperience(employeeDateOfJoin) > 18250) {
                    logger.info("Invalid");
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            } catch (DateTimeParseException exception) {
                logger.info("Invalid");
            }
        }
        return employeeDateOfJoin;
    }

    /**
     * used to display trainers details
     *
     * @return {@link void}
     */
    public void displayAllTrainers() throws EmptyListException {
        if (trainerDtoEmployeeService.getAllEmployees().size() == 0) {
            throw new EmptyListException("List is Empty");
        } else {
            for (TrainerDto trainerDto : trainerDtoEmployeeService.getAllEmployees()) {
                logger.info(trainerDto.toString());
            }
        }
    }

    /**
     * used to display trainees details
     *
     * @return {@link void}
     */
    public void displayAllTrainees() {
        for (TraineeDto traineeDto : traineeDtoEmployeeService.getAllEmployees()) {
            logger.info(traineeDto.toString());
        }
    }


    /**
     * used to display employee details with employee Id
     *
     * @return {@link void}
     */
    public void displayEmployeById() {
        try {
            logger.info("Press 1 to display respective Trainer by entering their ");
            logger.info("Press 2 to display respective Trainee by entering their");
            int choiceOfUser = scanner.nextInt();
            if (choiceOfUser == 1) {
                String employeeId = getEmployeeIdFromUser();
                TrainerDto trainerDto = trainerDtoEmployeeService.getEmployeeById(employeeId);
                if (trainerDto != null) {
                    logger.info(trainerDto.toString());
                } else {
                    logger.info("NO SUCH ID");
                }
            } else if (choiceOfUser == 2) {
                String employeeId = getEmployeeIdFromUser();
                TraineeDto traineeDto = traineeDtoEmployeeService.getEmployeeById(employeeId);
                if (traineeDto != null) {
                    logger.info(traineeDto.toString());
                } else {
                    logger.info("NO SUCH ID");
                }
            }
        } catch (HibernateException e) {
            logger.info("Invalid id");
        }
    }

    public String getEmployeeIdFromUser() {
        logger.info("Enter Employee Id");
        String employeeId = scanner.next();
        return employeeId;
    }

    /**
     * used to associate trainer or trainee
     *
     * @return {void}
     */
    public void associateTrainersAndTrainees() throws InputMismatchException {
        logger.info("Enter 2 to add trainees to trainer");
        logger.info("Enter 1 to add trainers to trainee");
        int choiceOfUser = scanner.nextInt();
        if (choiceOfUser == 1) {
            List<String> trainersDto = new ArrayList<>();
            scanner.nextLine();
            logger.info("Enter the Trainer employeeId ");
            String traineeId = scanner.nextLine();

            logger.info("Enter the Trainers Id you want to add");

            String[] trainerIds = scanner.nextLine().split(",");
            for (int i = 0; i < trainerIds.length; i++) {
                trainersDto.add(trainerIds[i]);
            }
            traineeDtoEmployeeService.association(trainersDto, traineeId);
        } else if (choiceOfUser == 2) {
            List<String> traineesDto = new ArrayList<>();
            scanner.nextLine();
            logger.info("Enter the Trainer employeeId ");
            String trainerId = scanner.nextLine();

            logger.info("Enter the Trainees Id you want to add");

            String[] traineeIds = scanner.nextLine().split(",");

            for (int i = 0; i < traineeIds.length; i++) {
                traineesDto.add(traineeIds[i]);
            }
            trainerDtoEmployeeService.association(traineesDto, trainerId);
        }
    }


    /**
     * used to display trainers and their assigned trainee details  and viceversa with employee Id
     *
     * @return {void}
     */
    public void displayTrainersAndTrainees() throws InputMismatchException {
        logger.info("Enter 1 to get details by trainer Id ");
        logger.info("Enter 2 to get details by Trainee Id ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            logger.info("Enter Trainer Id");
            String employeeId = scanner.next();
            TrainerDto trainerDto = trainerDtoEmployeeService.displayTrainerAndTrainee(employeeId);
            if (trainerDto != null) {
                logger.info("Trainer details " + trainerDto.toString());
                for (TraineeDto traineeDto : trainerDto.getTraineeDto()) {
                    logger.info(traineeDto.toString());
                }
            } else {
                logger.info("there is no such Id");
            }
        } else if (choice == 2) {
            logger.info("Enter Trainee Id");
            String employeeId = scanner.next();
            TraineeDto traineeDto = traineeDtoEmployeeService.displayTrainerAndTrainee(employeeId);
            if (traineeDto != null) {
                logger.info("Trainee details " + traineeDto.toString());
                for (TrainerDto trainerDto : traineeDto.getTrainerDto()) {
                    logger.info("Trainer details" + trainerDto.toString());
                }
            } else {
                logger.info("There is no such ID");
            }
        }
    }

    /**
     * used to update employee details with employee Id
     *
     * @return {void}
     */

    public void updateEmployeePhoneNumber() throws InputMismatchException {
        logger.info("Enter 1 to update Trainer phoneNumber");
        logger.info("Enter 2 to update Trainee phoneNumber");
        int choice = scanner.nextInt();

        if (choice == 1) {
            String employeeId = getEmployeeIdFromUser();
            if (trainerDtoEmployeeService.getEmployeeById(employeeId) != null) {
                logger.info("Enter phoneNumber to update");
                long employeePhoneNumber = getPhoneNumber();
                trainerDtoEmployeeService.updateEmployeePhoneNumber(employeeId, employeePhoneNumber);
                logger.info("updated successfully");
            } else {
                logger.info("Invalid");
            }
        } else if (choice == 2) {
            String employeeId = getEmployeeIdFromUser();
            boolean flag = true;
            if (traineeDtoEmployeeService.getEmployeeById(employeeId) != null) {
                logger.info("Enter phoneNumber to update");
                long employeePhoneNumber = getPhoneNumber();
                traineeDtoEmployeeService.updateEmployeePhoneNumber(employeeId, employeePhoneNumber);
                logger.info("updated successfully");
            } else {
                logger.info("invalid");
            }
        }
    }

    /**
     * used to delete employee details with employee Id
     *
     * @return {void}
     */

    public void deleteEmployeeById() throws InputMismatchException {
        logger.info("Enter 1 to delete Trainer details");
        logger.info("Enter 2 to delete Trainee details");
        int choice = scanner.nextInt();
        if (choice == 1) {
            String employeeId = getEmployeeIdFromUser();
            if (trainerDtoEmployeeService.getEmployeeById(employeeId) != null) {
                trainerDtoEmployeeService.deleteEmployeeById(employeeId);
                logger.info("deleted successfully");
            } else {
                logger.info("There is no such Id ");
            }
        } else if (choice == 2) {
            String employeeId = getEmployeeIdFromUser();
            if (trainerDtoEmployeeService.getEmployeeById(employeeId) != null) {
                traineeDtoEmployeeService.deleteEmployeeById(employeeId);
                logger.info("deleted successfully");
            } else {
                logger.info("There is no such Id ");
            }
        }
    }
}
	    