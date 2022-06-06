import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';

@Injectable({ providedIn: 'root' })
export class DataService {

  constructor(private http: HttpClient) {
  }

  updateStudent(data: any, userId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/student/new-student/${userId}`;
    return this.http.patch(url, data);
  }

  generateFirstTest(classType, subjectId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/subjectId/${subjectId}/test/classType/${classType}`;
    return this.http.get(url);
  }

  checkTestResults(data, subjectId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/subjectId/${subjectId}/test/check-results`;
    return this.http.post(url, data);
  }

  createSubject(data: any): Observable<any> {
    const url = `${environment.apiUrl}/api/subject/`;
    return this.http.post(url, data);
  }

  getSubjects(): Observable<any> {
    const url = `${environment.apiUrl}/api/subject/`;
    return this.http.get(url);
  }

  getTeachers(): Observable<any> {
    const url = `${environment.apiUrl}/api/teacher/get-all-teachers`;
    return this.http.get(url);
  }

  getAllProfessions(): Observable<any> {
    const url = `${environment.apiUrl}/api/professional/`;
    return this.http.get(url);
  }

  getAllCoursesGroupsByProfessionId(id: any): Observable<any> {
    const url = `${environment.apiUrl}/api/professional/${id}/course-group/`;
    return this.http.get(url);
  }

  getAggregatedDataForChart(id: any): Observable<any> {
    const url = `${environment.apiUrl}/api/professional/${id}/course-group/aggregated-by-course`;
    return this.http.get(url);
  }

  getAllCoursesByGroupId(id: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${id}/course/`;
    return this.http.get(url);
  }

  getAllCoursesByUserId(id: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${id}/course/filtered`;
    return this.http.get(url);
  }

  getCourseByCourseId(groupId, courseId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${groupId}/course/${courseId}`;
    return this.http.get(url);
  }

  getAllModulesByCourseId(courseGroupId, courseId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${courseGroupId}/course/${courseId}/module/`;
    return this.http.get(url);
  }

  getAllChaptersByModuleId(moduleId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/module/${moduleId}/chapter/`;
    return this.http.get(url);
  }

  getAllExercisesByChapterId(chapterId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/chapter/${chapterId}/exercise/`;
    return this.http.get(url);
  }

  createCourseGroup(body, professionalId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/professional/${professionalId}/course-group/`;
    return this.http.post(url, body);
  }

  createCourse(body, courseGroupId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${courseGroupId}/course/`;
    return this.http.post(url, body);
  }

  createModule(body, courseGroupId, courseId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${courseGroupId}/course/${courseId}/module/`;
    return this.http.post(url, body);
  }

  createChapter(body, moduleId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/module/${moduleId}/chapter/`;
    return this.http.post(url, body);
  }

  createExercise(body, chapterId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/chapter/${chapterId}/exercise/`;
    return this.http.post(url, body);
  }

  updateCourseGroup(body, professionalId, courseGroupId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/professional/${professionalId}/course-group/${courseGroupId}`;
    return this.http.patch(url, body);
  }

  updateCourse(body, courseGroupId, courseId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${courseGroupId}/course/${courseId}`;
    return this.http.patch(url, body);
  }

  updateModule(body, courseGroupId, courseId, moduleId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/course-group/${courseGroupId}/course/${courseId}/module/${moduleId}`;
    return this.http.patch(url, body);
  }

  updateChapter(body, moduleId, chapterId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/module/${moduleId}/chapter/${chapterId}`;
    return this.http.patch(url, body);
  }

  updateExercise(body, chapterId, exerciseId: any): Observable<any> {
    const url = `${environment.apiUrl}/api/chapter/${chapterId}/exercise/${exerciseId}`;
    return this.http.patch(url, body);
  }
}
