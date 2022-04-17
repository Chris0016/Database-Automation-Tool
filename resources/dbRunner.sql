CREATE TABLE `departments` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `founded` int NOT NULL,
  `funds` int NOT NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_name_UNIQUE` (`department_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) NOT NULL,
  `department_id` int NOT NULL,
  `semester` varchar(45) DEFAULT NULL,
  `isOnline` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `department_id_idx` (`department_id`),
  CONSTRAINT `department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `faculty` (
  `faculty_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `phone` int NOT NULL,
  `department_id` int NOT NULL,
  `course1_id` int NOT NULL,
  `course2_id` int DEFAULT NULL,
  `course3_id` int DEFAULT NULL,
  `course4_id` int DEFAULT NULL,
  PRIMARY KEY (`faculty_id`),
  KEY `faculty_department_id_idx` (`department_id`),
  KEY `faculty_course1_id_idx` (`course1_id`),
  KEY `faculty_course2_id_idx` (`course2_id`),
  KEY `faculty_course3_id_idx` (`course3_id`),
  KEY `faculty_course4_id_idx` (`course4_id`),
  CONSTRAINT `faculty_course1_id` FOREIGN KEY (`course1_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `faculty_course2_id` FOREIGN KEY (`course2_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `faculty_course3_id` FOREIGN KEY (`course3_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `faculty_course4_id` FOREIGN KEY (`course4_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `faculty_department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `students` (
  `studentid` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` int NOT NULL,
  `age` int DEFAULT NULL,
  `credits` int NOT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`studentid`),
  UNIQUE KEY `studentid_UNIQUE` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


