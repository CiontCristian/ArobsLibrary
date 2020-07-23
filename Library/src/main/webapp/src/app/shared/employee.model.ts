export class Employee{
  id: number;
  name: string;
  email: string;
  role: string;
  password: string;


  constructor(id: number, name: string, email: string, role: string, password: string) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
    this.password = password;
  }
}
