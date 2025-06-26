import tkinter
from tkinter import ttk
from tkinter import messagebox

def enter_data():
    accepted = accept_var.get()
    
    if accepted == "Accepted":
        firstname = first_name_entry.get()
        lastname = last_name_entry.get()
        
        if firstname and lastname:
            title = title_combobox.get()
            age = age_spinbox.get()
            nationality = nationality_combobox.get()
            registration_status = reg_status_var.get()
            numcourses = numcourses_spinbox.get()
            numsemesters = numsemesters_spinbox.get()
            
            print("First name:", firstname, "Last name:", lastname)
            print("Title:", title, "Age:", age, "Nationality:", nationality)
            print("Courses:", numcourses, "Semesters:", numsemesters)
            print("Registration status:", registration_status)
            print("------------------------------------------")
        else:
            messagebox.showwarning(title="Error", message="First name and last name are required.")
    else:
        messagebox.showwarning(title="Error", message="You have not accepted the terms")

# Main window
window = tkinter.Tk()
window.title("Student Registration Form")
window.configure(bg="#f0f8ff")

frame = tkinter.Frame(window, bg="#f0f8ff")
frame.pack(padx=10, pady=10)

# Style
style = ttk.Style()
style.configure("TLabel", font=("Arial", 10), background="#f0f8ff")
style.configure("TButton", font=("Arial", 10, "bold"), foreground="white", background="#4682b4")
style.configure("TCombobox", padding=5)

# User Information Frame
user_info_frame = tkinter.LabelFrame(frame, text="User Information", bg="#e6f2ff", font=("Arial", 12, "bold"), fg="black", relief="groove", bd=2)
user_info_frame.grid(row=0, column=0, padx=20, pady=10)

first_name_label = tkinter.Label(user_info_frame, text="First Name:", bg="#e6f2ff", font=("Arial", 10))
last_name_label = tkinter.Label(user_info_frame, text="Last Name:", bg="#e6f2ff", font=("Arial", 10))
first_name_label.grid(row=0, column=0)
last_name_label.grid(row=0, column=1)

first_name_entry = tkinter.Entry(user_info_frame)
last_name_entry = tkinter.Entry(user_info_frame)
first_name_entry.grid(row=1, column=0)
last_name_entry.grid(row=1, column=1)

title_label = tkinter.Label(user_info_frame, text="Title:", bg="#e6f2ff", font=("Arial", 10))
title_combobox = ttk.Combobox(user_info_frame, values=["", "Mr.", "Ms.", "Dr."], state="readonly")
title_label.grid(row=0, column=2)
title_combobox.grid(row=1, column=2)

age_label = tkinter.Label(user_info_frame, text="Age:", bg="#e6f2ff", font=("Arial", 10))
age_spinbox = tkinter.Spinbox(user_info_frame, from_=18, to=110)
age_label.grid(row=2, column=0)
age_spinbox.grid(row=3, column=0)

nationality_label = tkinter.Label(user_info_frame, text="State:", bg="#e6f2ff", font=("Arial", 10))
nationality_combobox = ttk.Combobox(user_info_frame, values=[
    "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
    "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala",
    "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha"
], state="readonly")

nationality_label.grid(row=2, column=1)
nationality_combobox.grid(row=3, column=1)

for widget in user_info_frame.winfo_children():
    widget.grid_configure(padx=10, pady=5)

# Course Info Frame
courses_frame = tkinter.LabelFrame(frame, text="Course Information", bg="#ffe6e6", font=("Arial", 12, "bold"), fg="black", relief="groove", bd=2)
courses_frame.grid(row=1, column=0, sticky="news", padx=20, pady=10)

registered_label = tkinter.Label(courses_frame, text="Registration Status:", bg="#ffe6e6", font=("Arial", 10))
reg_status_var = tkinter.StringVar(value="Not Registered")
registered_check = tkinter.Checkbutton(courses_frame, text="Currently Registered",
                                       variable=reg_status_var, onvalue="Registered", offvalue="Not registered", bg="#ffe6e6")

registered_label.grid(row=0, column=0)
registered_check.grid(row=1, column=0)

numcourses_label = tkinter.Label(courses_frame, text="Completed Courses:", bg="#ffe6e6", font=("Arial", 10))
numcourses_spinbox = tkinter.Spinbox(courses_frame, from_=0, to=20)
numcourses_label.grid(row=0, column=1)
numcourses_spinbox.grid(row=1, column=1)

numsemesters_label = tkinter.Label(courses_frame, text="Semesters Completed:", bg="#ffe6e6", font=("Arial", 10))
numsemesters_spinbox = tkinter.Spinbox(courses_frame, from_=0, to=12)
numsemesters_label.grid(row=0, column=2)
numsemesters_spinbox.grid(row=1, column=2)

for widget in courses_frame.winfo_children():
    widget.grid_configure(padx=10, pady=5)

# Terms and Conditions Frame
terms_frame = tkinter.LabelFrame(frame, text="Terms & Conditions", bg="#f5f5dc", font=("Arial", 12, "bold"), fg="black", relief="groove", bd=2)
terms_frame.grid(row=2, column=0, sticky="news", padx=20, pady=10)

accept_var = tkinter.StringVar(value="Not Accepted")
terms_check = tkinter.Checkbutton(terms_frame, text="I accept the terms and conditions.",
                                  variable=accept_var, onvalue="Accepted", offvalue="Not Accepted", bg="#f5f5dc", font=("Arial", 10))
terms_check.grid(row=0, column=0, padx=10, pady=5)

# Submit Button
button = tkinter.Button(frame, text="Enter Data", bg="#4682b4", fg="white", font=("Arial", 12, "bold"),
                        command=enter_data)
button.grid(row=3, column=0, sticky="news", padx=20, pady=10)

window.mainloop()
