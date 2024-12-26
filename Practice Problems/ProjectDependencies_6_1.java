import java.util.*;

public class ProjectDependencies_POD_06_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, Integer> projects = new HashMap<>();
        Map<String, List<String>> dependencies = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String project = sc.nextLine();
            String[] tasks = sc.nextLine().split(",");
            projects.put(project, tasks.length);
            for (String task : tasks) {
                if(!dependencies.containsKey(task)) {
                    dependencies.put(task, new ArrayList<>());
                }
                dependencies.get(task).add(project);
            }
        }
        String[] taskOrder = sc.nextLine().split(",");
        List<String> projectsCompleted = getProjectCompletionOrder(taskOrder, projects, dependencies);
        if (projectsCompleted == null || projectsCompleted.isEmpty()) {
            System.out.println("NONE");
        } else {
            projectsCompleted.forEach(System.out::println);
        }
    }

    private static List<String> getProjectCompletionOrder(String[] taskOrder, Map<String, Integer> projects, Map<String, List<String>> dependencies) {
        List<String> projectsCompletion = new ArrayList<>();
        for (String task : taskOrder) {
            List<String> completedProjects = clearDependency(projects, dependencies, task);
            projectsCompletion.addAll(completedProjects);
        }
        if (!projects.isEmpty()) {
            return null;
        }
        return projectsCompletion;
    }

    private static List<String> clearDependency(Map<String, Integer> projects, Map<String, List<String>> dependencies, String task) {
        List<String> projectsCompletedWithoutDependecies = new ArrayList<>();
        List<String> projectsCompletedWithDependecies = new ArrayList<>();
        List<String> deps = dependencies.getOrDefault(task, new ArrayList<>());
        for (String dep : deps) {
            projects.put(dep, projects.get(dep) - 1);
            if (projects.get(dep) == 0) {
                projectsCompletedWithoutDependecies.add(dep);
                projectsCompletedWithDependecies = clearDependency(projects, dependencies, dep);
                projects.remove(dep);
            }
        }
        projectsCompletedWithoutDependecies.sort(Comparator.naturalOrder());
        projectsCompletedWithoutDependecies.addAll(projectsCompletedWithDependecies);
        return projectsCompletedWithoutDependecies;
    }

}
