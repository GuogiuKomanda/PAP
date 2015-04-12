package imports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lt.pap.config.SpringDataConfig;
import lt.pap.model.Make;
import lt.pap.model.Model;
import lt.pap.model.ModelGroup;
import lt.pap.service.EngineService;
import lt.pap.service.FuelTypeService;
import lt.pap.service.MakeService;
import lt.pap.service.ModelEngineService;
import lt.pap.service.ModelGroupService;
import lt.pap.service.ModelService;
import lt.pap.service.PartService;
import lt.pap.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringDataConfig.class })
public class ImportDB {

	private static String IMPPORT_DIR = "C:\\pap\\import\\";
	private static String FILE_NAME = "TestCSVms.csv";

	private static DateTimeFormatter yearMonthFormatter = DateTimeFormatter
			.ofPattern("MM.yyyy");

	@Autowired
	private UserService userService;

	@Autowired
	private PartService partService;

	@Autowired
	private MakeService makeService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private ModelGroupService modelGroupService;

	@Autowired
	private EngineService engineService;

	@Autowired
	private ModelEngineService modelEngineService;

	@Autowired
	private FuelTypeService fuelTypeService;

	@Test
	public void importFile() {
		try {
			readLines();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private void readHeader() throws IOException {
	//
	// Stream<String> lines = Files.lines(Paths.get(IMPPORT_DIR, FILE_NAME));
	// Model model = lines.findFirst().map(mapToModel).get();
	// lines.close();
	// }

	private void readLines() throws IOException {

		Stream<String> lines = Files.lines(Paths.get(IMPPORT_DIR, FILE_NAME));

		lines.skip(1).map(mapToMake).collect(Collectors.toList());

		lines.close();
	}

	public Function<String, Make> mapToMake = (line) -> {
		String[] items = line.split(";");

		String makeName = items[0];
		Make make = getMake(makeName);

		String mgName = items[1];
		ModelGroup modelGroup = getModelGroup(make, mgName);

		String modelName = items[2];
		String fromString = items[3];
		String toString = items[4];

		YearMonth from = YearMonth.parse(fromString, yearMonthFormatter);
		YearMonth to = toString.equals("today") ? null : YearMonth.parse(toString, yearMonthFormatter);
		Model model = getModel(modelGroup, modelName, from, to);

		return make;
	};

	private Make getMake(String name) {
		Make make = makeService.findByName(name);
		if (make == null) {
			make = new Make();
			make.setName(name);
			makeService.save(make);
		}
		return make;
	}

	private ModelGroup getModelGroup(Make make, String mgName) {
		ModelGroup current;
		List<ModelGroup> list = make.getModelGroupList().stream()
				.filter(mg -> mg.getName().equals(mgName))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			current = new ModelGroup();
			current.setMake(make);
			current.setName(mgName);
			modelGroupService.save(current);
		} else {
			current = list.get(0);
		}
		return current;
	}

	private Model getModel(ModelGroup modelGroup, String modelName,
			YearMonth from, YearMonth to) {
		Model current;
		List<Model> list = modelGroup.getModelList().stream()
				.filter(m -> m.getName().equals(modelName))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			current = new Model();
			current.setModelGroup(modelGroup);
			current.setName(modelName);
			current.setFrom(from);
			current.setTo(to);
			modelService.save(current);
		} else {
			current = list.get(0);
		}
		return current;
	}
}