import { Component } from '@angular/core';

export interface CourseDetails {
  courseId: string;
  courseName: string;
  courseDescription: string;
  courseImg: string;
  coursePrice: number;
  courseCategory: string;
  courseCreatorName: string;
  courseCreatorPhone: string;
  courseCreatorEmail: string;
}
