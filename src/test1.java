import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class test1 
{
	public static void main(String args[]) throws Exception
	{
		/* Take in input from kinect and convert it to KB result */
		
		FreeTTS speaker = new FreeTTS();
		speaker.speak("Hello, welcome to the Captis project. What is your name?.");
		Scanner scanner = new Scanner( System.in );
		String input = scanner.nextLine();
		speaker.speak(input + ". That's a nice name.");
		
		
		ConceptMap map = new ConceptMap();
		ConceptFrame e1 = new ConceptFrame("Exercise1", "exercise");
		ConceptFrame e2 = new ConceptFrame("Exercise2", "exercise");
		ConceptFrame c1 = new ConceptFrame("Condition1", "condition");
		ConceptFrame c2 = new ConceptFrame("Condition2", "condition");
		ConceptFrame c3 = new ConceptFrame("Condition3", "condition");
		ConceptFrame A = new ConceptFrame("A", "movement");
		ConceptFrame B = new ConceptFrame("B", "movement");
		ConceptFrame C = new ConceptFrame("C", "movement");
		ConceptFrame D = new ConceptFrame("D", "movement");
		ConceptFrame j1 = new ConceptFrame("Right Hand", "joint");
		ConceptFrame j2 = new ConceptFrame("Chest", "joint");
		ConceptFrame j3 = new ConceptFrame("Left Hand", "joint");
		
		FrameFactory h = new FrameFactory(map);
		
		ConceptFrame jr1 = h.createJointRelationFrame(j1, j2, "leftof");
		ConceptFrame jr2 = h.createJointRelationFrame(j1, j3, "leftof");
		ConceptFrame jr3 = h.createJointRelationFrame(j2, j1, "rightof");
		ConceptFrame jr4 = h.createJointRelationFrame(j2, j3, "leftof");
		ConceptFrame jr5 = h.createJointRelationFrame(j3, j1, "rightof");
		ConceptFrame jr6 = h.createJointRelationFrame(j3, j2, "rightof");
		
		ArrayList<ConceptFrame> jointRelations = new ArrayList<ConceptFrame>(Arrays.asList(jr1,jr2,jr3,jr4,jr5,jr6));
		ConceptFrame st1 = h.createStateFrame(jointRelations, 1);
		
		ConceptFrame jr7 = h.createJointRelationFrame(j1, j2, "above");
		ConceptFrame jr8 = h.createJointRelationFrame(j1, j3, "leftof");
		ConceptFrame jr9 = h.createJointRelationFrame(j2, j1, "below");
		ConceptFrame jr10 = h.createJointRelationFrame(j2, j3, "below");
		ConceptFrame jr11 = h.createJointRelationFrame(j3, j1, "rightof");
		ConceptFrame jr12 = h.createJointRelationFrame(j3, j2, "above");

		ArrayList<ConceptFrame> jointRelations2 = new ArrayList<ConceptFrame>(Arrays.asList(jr7,jr8,jr9,jr10,jr11,jr12));
		ConceptFrame st2 = h.createStateFrame(jointRelations2, 2);

		ConceptFrame jr13 = h.createJointRelationFrame(j1, j2, "below");
		ConceptFrame jr14 = h.createJointRelationFrame(j1, j3, "leftof");
		ConceptFrame jr15 = h.createJointRelationFrame(j2, j1, "above");
		ConceptFrame jr16 = h.createJointRelationFrame(j2, j3, "above");
		ConceptFrame jr17 = h.createJointRelationFrame(j3, j1, "rightof");
		ConceptFrame jr18 = h.createJointRelationFrame(j3, j2, "below");

		ArrayList<ConceptFrame> jointRelations3 = new ArrayList<ConceptFrame>(Arrays.asList(jr13,jr14,jr15,jr16,jr17,jr18));
		ConceptFrame st3 = h.createStateFrame(jointRelations3, 3);


		Slot s1 = new Slot("state", "[x,leftof,leftof;rightof,x,leftof;rightof,rightof,x]");
		Slot s2 = new Slot("state", "[x,above,leftof;below,x,below;rightof,above,x]");
		Slot s3 = new Slot("state", "[x,below,leftof;above,x,above;rightof,below,x]");
		
		Slot s4 = new Slot("name", "Raise Arms");
		Slot s5 = new Slot("name", "Lower Arms");
		Slot s6 = new Slot("numberOfMovements", "2");
		Slot s7 = new Slot("numberOfMovements", "2");
		c1.addSlot(s1);
		c2.addSlot(s2);
		c3.addSlot(s3);
		e1.addSlot(s4);
		e1.addSlot(s6);
		e2.addSlot(s5);
		e2.addSlot(s7);
		
		map.addFrame(A);
		map.addFrame(B);
		map.addFrame(C);
		map.addFrame(D);
		map.addFrame(c1);
		map.addFrame(c2);
		map.addFrame(c3);
		map.addFrame(e1);
		map.addFrame(e2);
		map.addFrame(st1);
		map.addFrame(st2);
		map.addFrame(st3);
		map.addFrame(j1);
		map.addFrame(j2);
		map.addFrame(j3);
		
		for (ConceptFrame f : jointRelations)
		{
			map.addFrame(f);
		}
		
		for (ConceptFrame f : jointRelations2)
		{
			map.addFrame(f);
		}
		
		for (ConceptFrame f : jointRelations3)
		{
			map.addFrame(f);
		}
		
		speaker.speak("These are the exercises I know.");
		for (ConceptFrame f : map.nodes)
		{
			if (f.type == "exercise")
				speaker.speak(f.getValue("name") + " with " + f.getValue("numberOfMovements") + " movements.");
		}
		
		Thread.sleep(300);
		speaker.speak("Please perform a movement");
		
		try {
			map.addEdge("hasMovement1", e1, A);
			map.addEdge("isMovement1", A, e1);
			map.addEdge("hasMovement2", e1, B);
			map.addEdge("isMovement2", B, e1);
			map.addEdge("startsWith", e1, A);
			map.addEdge("endsWith", e1, B);
			
			map.addEdge("hasMovement1", e2, C);
			map.addEdge("isMovement1", C, e2);
			map.addEdge("hasMovement2", e2, D);
			map.addEdge("isMovement2", D, e2);
			map.addEdge("startsWith", e2, C);
			map.addEdge("endsWith", e2, D);
			
			map.addEdge("hasPrecondition", A, c3);
			map.addEdge("isPrecondition", c3, A);
			map.addEdge("hasPostcondition", A, c1);
			map.addEdge("isPostcondition", c1, A);
			
			map.addEdge("hasPrecondition", B, c1);
			map.addEdge("isPrecondition", c1, B);
			map.addEdge("hasPostcondition", B, c2);
			map.addEdge("isPostcondition", c2, B);
			
			map.addEdge("hasPrecondition", C, c2);
			map.addEdge("isPrecondition", c2, C);
			map.addEdge("hasPostcondition", C, c1);
			map.addEdge("isPostcondition", c1, C);
			
			map.addEdge("hasPrecondition", D, c1);
			map.addEdge("isPrecondition", c1, D);
			map.addEdge("hasPostcondition", D, c3);
			map.addEdge("isPostcondition", c3, D);
			
			map.addEdge("hasPreState", A, st3);
			map.addEdge("isPreState", st3, A);
			map.addEdge("hasPostState", A, st1);
			map.addEdge("isPostState", st1, A);
			
			map.addEdge("hasPreState", B, st1);
			map.addEdge("isPreState", st1, B);
			map.addEdge("hasPostState", B, st2);
			map.addEdge("isPostState", st2, B);
			
			map.addEdge("hasPreState", C, st2);
			map.addEdge("isPreState", st2, C);
			map.addEdge("hasPostState", C, st1);
			map.addEdge("isPostState", st1, C);
			
			map.addEdge("hasPreState", D, st1);
			map.addEdge("isPreState", st1, D);
			map.addEdge("hasPostState", D, st3);
			map.addEdge("isPostState", st3, D);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(1500);
		Evaluator eval = new Evaluator(map);
		// Abstracted input
		ArrayList<String> states = new ArrayList<String>();
		states.add("[x,below,leftof;above,x,above;rightof,below,x]");
		states.add("[x,leftof,leftof;rightof,x,leftof;rightof,rightof,x]");
		states.add("[x,above,leftof;below,x,below;rightof,above,x]");
		
		ArrayList<String> states2 = new ArrayList<String>();
		states2.add("[x,below,leftof;above,x,above;rightof,below,x]");
		states2.add("[x,leftof,leftof;rightof,x,leftof;rightof,rightof,x]");
		states2.add("[x,above,leftof;below,x,below;rightof,above,x]");
		states2.add("[x,leftof,leftof;rightof,x,leftof;rightof,rightof,x]");
		states2.add("[x,below,leftof;above,x,above;rightof,below,x]");
		
		for (int i = 0; i < 2; i++)
		{
			ArrayList<String> curr;
			if (i==0)
				curr = states;
			else
				curr = states2;
			
			speaker.speak("You performed the exercise well.");
			
			ConceptFrame foundExercise = eval.findExercise(curr);
			speaker.speak("I see you did " + foundExercise.getValue("name"));
			
			String strategy = "complexity";
			CreativeDirector director = new CreativeDirector(map);
			
			if (strategy.equals("complexity"))
			{
				speaker.speak("Since you performed well. I will make the exercise more difficult.");
				ConceptFrame newExercise = director.mergeExercise(foundExercise);
				speaker.speak("Great. I will add " + newExercise.getValue("name") + " to my knowledge base");
				map.addFrame(newExercise);
				Thread.sleep(300);
				speaker.speak("These are the exercises I know.");
				for (ConceptFrame f : map.nodes)
				{
					if (f.type == "exercise")
						speaker.speak(f.getValue("name") + " with " + f.getValue("numberOfMovements") + " movements.");
				}
				
				speaker.speak("You should now do " + newExercise.getValue("name"));
				Thread.sleep(2000);
			}
			else if (strategy.equals("disorder"))
			{
				speaker.speak("The strategy is " + strategy);
				director.createExercise(foundExercise);
			}
		}
		speaker.speak("You have performed flapping wings. Thank you for using Captis.");
	}
}
