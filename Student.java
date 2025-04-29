

class Data {
    private Date dob;

    public Data(String dob) throws ParseException {
        SimpleDateFormat sdf;
        if (dob.contains("-")) {
            if (dob.split("-")[0].length() == 4) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd-MM-yyyy");
            }
            this.dob = sdf.parse(dob);
        } else {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    public int calculateAge() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(this.dob);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
            (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }
}
class Student {
    private String name;
    private Data data;
     public Student1(String name, String dob) throws ParseException {
        this.name = name;
        this.data = new Data(dob);
    }
    public void displayInfo() {
        int age = data.calculateAge();
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
    }
 public static void main(String[] args) {
        try {
            Student1 student1 = new Student1("John Doe", "15-05-2000");
            student1.displayInfo();
            
            Student1 student2 = new Student1("Jane Smith", "2000-05-15");
            student2.displayInfo();    
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format");
        }
    }
}
